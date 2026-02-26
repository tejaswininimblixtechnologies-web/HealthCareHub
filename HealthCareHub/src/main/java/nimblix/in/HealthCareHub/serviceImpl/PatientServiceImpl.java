package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.helper.UploadImageHelper;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.model.PatientDocument;
import nimblix.in.HealthCareHub.repository.PatientDocumentRepository;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.request.PatientDocumentUploadRequest;
import nimblix.in.HealthCareHub.response.MultipleImageResponse;
import nimblix.in.HealthCareHub.response.PatientDocumentUploadResponse;
import nimblix.in.HealthCareHub.service.PatientService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientDocumentRepository patientDocumentRepository;
    private final UploadImageHelper uploadImageHelper;
    private final PatientRepository patientRepository;
    public PatientServiceImpl(PatientDocumentRepository patientDocumentRepository,
                              UploadImageHelper uploadImageHelper,
                              PatientRepository patientRepository) {
        this.patientDocumentRepository = patientDocumentRepository;
        this.uploadImageHelper = uploadImageHelper;
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientDocumentUploadResponse uploadPatientDocument(PatientDocumentUploadRequest request) {

        //  Validate patient exists
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException(HealthCareConstants.PATIENT_NOT_FOUND));

        //  Validate file
        MultipartFile file = request.getFile();

        if (file == null || file.isEmpty()) {
            throw new RuntimeException(HealthCareConstants.FILE_EMPTY);
        }

        // Validate filename
        String originalFileName = file.getOriginalFilename();

        if (originalFileName == null || !originalFileName.contains(".")) {
            throw new RuntimeException(HealthCareConstants.INVALID_FILE_TYPE);
        }

        // Extract extension
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();

        if (!(extension.equals(HealthCareConstants.FILE_TYPE_PDF) ||
                extension.equals(HealthCareConstants.FILE_TYPE_JPG) ||
                extension.equals(HealthCareConstants.FILE_TYPE_JPEG) ||
                extension.equals(HealthCareConstants.FILE_TYPE_PNG))) {

            throw new RuntimeException(HealthCareConstants.INVALID_FILE_TYPE);
        }

        //  Upload file using helper
        List<MultipartFile> files = new ArrayList<>();
        files.add(file);

        MultipleImageResponse uploadResponse;

        try {
            uploadResponse = uploadImageHelper.uploadImages(files);
        } catch (Exception e) {
            throw new RuntimeException(HealthCareConstants.FILE_UPLOAD_FAILED);
        }

        // Validate upload success
        if (uploadResponse == null ||
                uploadResponse.getUploadedFileNames() == null ||
                uploadResponse.getUploadedFileNames().isEmpty()) {
            throw new RuntimeException(HealthCareConstants.FILE_UPLOAD_FAILED);
        }

        String savedFileName = uploadResponse.getUploadedFileNames().get(0);

        //  Store metadata in DB
        PatientDocument document = new PatientDocument();
        document.setPatient(patient);
        document.setFileName(originalFileName);

        // IMPORTANT: store full metadata path
        String storedPath = HealthCareConstants.DOCUMENT_UPLOAD_DIR + savedFileName;
        document.setFilePath(storedPath);

        document.setDocumentType(request.getDocumentType());
        document.setDescription(request.getDescription());
        document.setUploadedAt(LocalDateTime.now());

        PatientDocument savedDocument = patientDocumentRepository.save(document);

        //  Prepare response (NOT entity)
        PatientDocumentUploadResponse response = new PatientDocumentUploadResponse();
        response.setDocumentId(savedDocument.getId());
        response.setFileName(savedDocument.getFileName());
        response.setDocumentType(savedDocument.getDocumentType());
        response.setUploadedAt(savedDocument.getUploadedAt());
        response.setMessage(HealthCareConstants.FILE_UPLOAD_SUCCESS);

        return response;
    }
}