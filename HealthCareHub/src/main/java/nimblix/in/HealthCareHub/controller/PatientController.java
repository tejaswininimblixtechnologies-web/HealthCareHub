package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Upload Patient Document
    @PostMapping("/upload/{patientId}")
    public ResponseEntity<?> uploadDocument(
            @PathVariable Long patientId,
            @RequestParam("file") MultipartFile file) {

        // 1️⃣ Check empty
        if (file == null || file.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Please select a file to upload");
        }

        // 2️⃣ Check size (5MB limit)
        if (file.getSize() > 5 * 1024 * 1024) {
            return ResponseEntity
                    .badRequest()
                    .body("File size should not exceed 5MB");
        }

        // 3️⃣ Check type
        String contentType = file.getContentType();
        if (!(contentType.equals("application/pdf")
                || contentType.equals("image/png")
                || contentType.equals("image/jpeg"))) {
            return ResponseEntity
                    .badRequest()
                    .body("Only PDF, JPG, PNG files are allowed");
        }

        patientService.uploadPatientDocument(patientId, file);

        return ResponseEntity.ok("Document uploaded successfully");
    }


    // Get all documents of patient
    @GetMapping("/{patientId}/documents")
    public ResponseEntity<?> getDocuments(@PathVariable Long patientId) {
        return ResponseEntity.ok(patientService.getPatientDocuments(patientId));
    }

    // Download document
    @GetMapping("/download/{documentId}")
    public ResponseEntity<?> downloadDocument(@PathVariable Long documentId) {
        return patientService.downloadDocument(documentId);
    }

    // Delete document
    @DeleteMapping("/document/{documentId}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long documentId) {
        patientService.deleteDocument(documentId);
        return ResponseEntity.ok("Document deleted successfully");
    }
}