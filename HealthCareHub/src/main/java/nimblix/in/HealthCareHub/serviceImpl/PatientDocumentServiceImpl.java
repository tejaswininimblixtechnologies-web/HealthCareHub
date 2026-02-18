package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.helper.UploadImageHelper;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.model.PatientDocument;
import nimblix.in.HealthCareHub.repository.PatientDocumentRepository;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.response.MultipleImageResponse;
import nimblix.in.HealthCareHub.service.PatientDocumentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientDocumentServiceImpl implements PatientDocumentService {

    private final PatientRepository patientRepository;
    private final PatientDocumentRepository documentRepository;
    private final UploadImageHelper uploadImageHelper;

    public PatientDocumentServiceImpl(PatientRepository patientRepository,
                                      PatientDocumentRepository documentRepository,
                                      UploadImageHelper uploadImageHelper) {
        this.patientRepository = patientRepository;
        this.documentRepository = documentRepository;
        this.uploadImageHelper = uploadImageHelper;
    }
    @Override
    public List<PatientDocument> getPatientDocuments(Long patientId) {
        return documentRepository.findByPatientId(patientId);
    }

    @Override
    public String uploadPatientDocument(Long patientId, MultipartFile file) {

        //  Check patient exists
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        //  Empty validation
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Please upload a file");
        }

        //  File size validation (10MB)
        long maxSize = 10 * 1024 * 1024;
        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException("File size must be less than 10MB");
        }

        //  File extension validation
        String fileName = file.getOriginalFilename();
        if (fileName == null ||
                !(fileName.toLowerCase().endsWith(".pdf")
                        || fileName.toLowerCase().endsWith(".png")
                        || fileName.toLowerCase().endsWith(".jpg")
                        || fileName.toLowerCase().endsWith(".jpeg"))) {

            throw new IllegalArgumentException("Invalid file extension. Only PDF, JPG, PNG allowed");
        }

        //  Content type validation
        String contentType = file.getContentType();
        if (contentType == null ||
                !(contentType.equals("application/pdf")
                        || contentType.equals("image/png")
                        || contentType.equals("image/jpeg"))) {

            throw new IllegalArgumentException("Only PDF, JPG, PNG allowed");
        }

        //  Convert file to list
        List<MultipartFile> fileList = new ArrayList<>();
        fileList.add(file);

        //  Upload using helper
        MultipleImageResponse response;

        try {
            response = uploadImageHelper.uploadImages(fileList);
        } catch (Exception e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }


        if (!"SUCCESS".equals(response.getStatus())) {
            throw new IllegalArgumentException("File upload failed");
        }

        //  Get saved filename
        String savedFileName = response.getUploadedFileNames().get(0);

        // Save metadata
        PatientDocument document = PatientDocument.builder()
                .fileName(savedFileName)
                .fileType(contentType)
                .filePath("uploads/" + savedFileName)
                .fileSize(file.getSize())
                .uploadedAt(LocalDateTime.now())
                .patient(patient)
                .build();

        documentRepository.save(document);

        return "Document Uploaded Successfully";
    }

    @Override
    public List<PatientDocument> getDocumentsByPatientId(Long patientId) {
        return documentRepository.findByPatientId(patientId);
    }


    @Override
    public void deleteDocument(Long documentId) {
        if (!documentRepository.existsById(documentId)) {
            throw new IllegalArgumentException("Document not found");
        }
        documentRepository.deleteById(documentId);
    }
}
