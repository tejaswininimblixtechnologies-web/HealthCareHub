package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.helper.UploadImageHelper;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.request.PatientDocumentUploadRequest;
import nimblix.in.HealthCareHub.response.MultipleImageResponse;
import nimblix.in.HealthCareHub.response.PatientDocumentUploadResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import nimblix.in.HealthCareHub.utility.HealthCareUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final UploadImageHelper uploadImageHelper;
    private final PatientRepository patientRepository;

    @Override
    public PatientDocumentUploadResponse uploadPatientDocument(
            long patientId,
            PatientDocumentUploadRequest request) {

        //  Validate patient
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new RuntimeException(HealthCareConstants.PATIENT_NOT_FOUND));

        //  Validate file
        MultipartFile file = request.getFile();

        if (file == null || file.isEmpty()) {
            throw new RuntimeException(HealthCareConstants.FILE_EMPTY);
        }

        //  Validate filename
        String originalFileName = file.getOriginalFilename();

        if (originalFileName == null || !originalFileName.contains(".")) {
            throw new RuntimeException(HealthCareConstants.INVALID_FILE_TYPE);
        }

        //  Check extension
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();

        if (!(extension.equals(HealthCareConstants.FILE_TYPE_PDF)
                || extension.equals(HealthCareConstants.FILE_TYPE_JPG)
                || extension.equals(HealthCareConstants.FILE_TYPE_JPEG)
                || extension.equals(HealthCareConstants.FILE_TYPE_PNG))) {

            throw new RuntimeException(HealthCareConstants.INVALID_FILE_TYPE);
        }

        //  Upload file
        List<MultipartFile> files = new ArrayList<>();
        files.add(file);

        MultipleImageResponse uploadResponse;

        try {
            uploadResponse = uploadImageHelper.uploadImages(files);
        } catch (Exception e) {
            throw new RuntimeException(HealthCareConstants.FILE_UPLOAD_FAILED);
        }

        if (uploadResponse == null ||
                uploadResponse.getUploadedFileNames() == null ||
                uploadResponse.getUploadedFileNames().isEmpty()) {

            throw new RuntimeException(HealthCareConstants.FILE_UPLOAD_FAILED);
        }

        //  Get saved filename
        String savedFileName = uploadResponse.getUploadedFileNames().get(0);

        //  Set values directly to patient
        patient.setDocumentName(originalFileName);
        patient.setDocumentPath(savedFileName);
        patient.setDocumentType(request.getDocumentType());
        patient.setUploadedAt(
                HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString()
        );

        //  Save patient
        patientRepository.save(patient);

        //  Prepare response
        PatientDocumentUploadResponse response = new PatientDocumentUploadResponse();
        response.setDocumentId(patient.getId());
        response.setFileName(patient.getDocumentName());
        response.setDocumentType(patient.getDocumentType());
        response.setUploadedAt(patient.getUploadedAt());
        response.setMessage(HealthCareConstants.FILE_UPLOAD_SUCCESS);

        return response;
    }
}