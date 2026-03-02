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
@RequiredArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {

    private final UploadImageHelper uploadImageHelper;
    private final PatientRepository patientRepository;
    @Override
    public PatientDocumentUploadResponse uploadPatientDocument(long patientId, PatientDocumentUploadRequest request) {

        // 1️⃣ Validate patient
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException(HealthCareConstants.PATIENT_NOT_FOUND));

        // 2️⃣ Validate file
        MultipartFile file = request.getFile();

        if (file == null || file.isEmpty()) {
            throw new RuntimeException(HealthCareConstants.FILE_EMPTY);
        }

        // 3️⃣ Validate filename
        String originalFileName = file.getOriginalFilename();

        if (originalFileName == null || !originalFileName.contains(".")) {
            throw new RuntimeException(HealthCareConstants.INVALID_FILE_TYPE);
        }

        // 4️⃣ Check extension
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();

        if (!(extension.equals(HealthCareConstants.FILE_TYPE_PDF) ||
                extension.equals(HealthCareConstants.FILE_TYPE_JPG) ||
                extension.equals(HealthCareConstants.FILE_TYPE_JPEG) ||
                extension.equals(HealthCareConstants.FILE_TYPE_PNG))) {

            throw new RuntimeException(HealthCareConstants.INVALID_FILE_TYPE);
        }

        // 5️⃣ Upload file using helper
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

        // 6️⃣ Get saved filename
        String savedFileName = uploadResponse.getUploadedFileNames().get(0);

        // 7️⃣ Create Document object (IMPORTANT — NOT Patient object)
        Patient.Document doc = new Patient.Document();
        doc.setDocumentName(originalFileName);

        doc.setDocumentType(request.getDocumentType());
        doc.setUploadedAt(
                HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString()
        );

        // 8️⃣ Add document into patient list
        patient.getDocuments().add(doc);

        // 9️⃣ Save patient (THIS saves into patient_documents table automatically)
        patientRepository.save(patient);

        // 🔟 Prepare response
        PatientDocumentUploadResponse response = new PatientDocumentUploadResponse();
        response.setDocumentId(patient.getId());
        response.setFileName(doc.getDocumentName());
        response.setDocumentType(doc.getDocumentType());
        response.setUploadedAt(doc.getUploadedAt());
        response.setMessage(HealthCareConstants.FILE_UPLOAD_SUCCESS);

        return response;
    }
}