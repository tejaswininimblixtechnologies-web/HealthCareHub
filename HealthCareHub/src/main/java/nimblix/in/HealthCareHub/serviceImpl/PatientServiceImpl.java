package nimblix.in.HealthCareHub.serviceImpl;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.helper.UploadImageHelper;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.model.PatientDocument;
import nimblix.in.HealthCareHub.repository.PatientDocumentRepository;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientDocumentRepository patientDocumentRepository;
    private final UploadImageHelper uploadImageHelper;

    // ================= UPLOAD =================
    @Override
    public String uploadPatientDocument(Long patientId, MultipartFile file) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // upload file to folder
        String filePath = uploadImageHelper.uploadFile(file);

        // save record in DB
        PatientDocument document = new PatientDocument();
        document.setFileName(file.getOriginalFilename());
        document.setFileType(file.getContentType());
        document.setFilePath(filePath);
        document.setFileSize(file.getSize());
        document.setUploadedAt(LocalDateTime.now());
        document.setPatient(patient);

        patientDocumentRepository.save(document);

        return "Document uploaded successfully";
    }

    // ================= VIEW =================
    @Override
    public List<PatientDocument> getPatientDocuments(Long patientId) {
        return patientDocumentRepository.findByPatientId(patientId);
    }

    // ================= INTERNAL FILE FETCH =================
    private File getDocumentFile(Long documentId) {

        PatientDocument document = patientDocumentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        String fullPath = System.getProperty("user.dir")
                + File.separator
                + document.getFilePath();

        File file = new File(fullPath);

        if (!file.exists()) {
            throw new RuntimeException("File not found at: " + fullPath);
        }

        return file;
    }

    // ================= DOWNLOAD =================
    @Override
    public ResponseEntity<?> downloadDocument(Long documentId) {

        try {
            File file = getDocumentFile(documentId);

            InputStreamResource resource =
                    new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=" + file.getName())
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (Exception e) {
            throw new RuntimeException("Error while downloading file");
        }
    }

    // ================= DELETE =================
    @Override
    public void deleteDocument(Long documentId) {

        PatientDocument document = patientDocumentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        // delete from folder
        String fullPath = System.getProperty("user.dir")
                + File.separator
                + document.getFilePath();

        File file = new File(fullPath);
        if (file.exists()) {
            file.delete();
        }

        // delete DB record
        patientDocumentRepository.delete(document);
    }

    @Override
    public Resource downloadPatientDocument(Long documentId) {
        return null;
    }
}