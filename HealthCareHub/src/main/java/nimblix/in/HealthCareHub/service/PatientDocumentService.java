package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.PatientDocument;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PatientDocumentService {

    String uploadPatientDocument(Long patientId, MultipartFile file);

    List<PatientDocument> getPatientDocuments(Long patientId);   // ⭐ ADD THIS

    List<PatientDocument> getDocumentsByPatientId(Long patientId);

    void deleteDocument(Long documentId);
}
