package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.PatientDocument;
import nimblix.in.HealthCareHub.service.PatientDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientDocumentController {

    private final PatientDocumentService patientDocumentService;

    public PatientDocumentController(PatientDocumentService patientDocumentService) {
        this.patientDocumentService = patientDocumentService;
    }

    // Upload Patient Document
    @PostMapping("/{patientId}/documents")
    public ResponseEntity<String> uploadDocument(
            @PathVariable Long patientId,
            @RequestParam(defaultValue = "file", required = false) MultipartFile file) {

        String response = patientDocumentService.uploadPatientDocument(patientId, file);
        return ResponseEntity.ok(response);
    }

    // Get all documents of patient
    @GetMapping("/{patientId}/documents")
    public ResponseEntity<?> getDocuments(@PathVariable Long patientId) {
        return ResponseEntity.ok(patientDocumentService.getPatientDocuments(patientId));
    }


    // Delete document
    @DeleteMapping("/documents/{documentId}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long documentId) {
        patientDocumentService.deleteDocument(documentId);
        return ResponseEntity.ok("Document deleted successfully");
    }
}

