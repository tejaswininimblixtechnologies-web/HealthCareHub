package nimblix.in.HealthCareHub.service;

import jakarta.annotation.Resource;
import nimblix.in.HealthCareHub.model.PatientDocument;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.UrlResource;
import java.nio.file.Path;
import java.nio.file.Paths;


import java.util.List;

public interface PatientService {

    // upload patient report
    String uploadPatientDocument(Long patientId, MultipartFile file);

    // view patient reports
    List<PatientDocument> getPatientDocuments(Long patientId);

    // download report
    ResponseEntity<?> downloadDocument(Long documentId);

    // delete report
    void deleteDocument(Long documentId);

    Resource downloadPatientDocument(Long documentId);
}
