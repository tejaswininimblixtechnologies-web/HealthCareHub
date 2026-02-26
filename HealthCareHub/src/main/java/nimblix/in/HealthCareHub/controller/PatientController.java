package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.PatientDocumentUploadRequest;
import nimblix.in.HealthCareHub.response.PatientDocumentUploadResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping(value = "/upload-document", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PatientDocumentUploadResponse> uploadPatientDocument(
            @ModelAttribute PatientDocumentUploadRequest request) {

        PatientDocumentUploadResponse response = patientService.uploadPatientDocument(request);
        return ResponseEntity.ok(response);
    }
}