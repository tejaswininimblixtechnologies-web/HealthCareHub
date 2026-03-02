package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.request.PatientDocumentUploadRequest;
import nimblix.in.HealthCareHub.response.PatientDocumentUploadResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping(value = "/{patientId}/upload-document",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PatientDocumentUploadResponse> uploadPatientDocument(

            @PathVariable("patientId") Long patientId,
            @ModelAttribute PatientDocumentUploadRequest request
    ) {

        // Edge Case 1: invalid id
        if (patientId == null || patientId <= 0) {
            throw new RuntimeException(HealthCareConstants.INVALID_PATIENT_ID);
        }

        // Edge Case 2: file missing
        if (request.getFile() == null || request.getFile().isEmpty()) {
            throw new RuntimeException(HealthCareConstants.FILE_REQUIRED);
        }

        // Call service
        PatientDocumentUploadResponse response =
                patientService.uploadPatientDocument(patientId, request);

        return ResponseEntity.ok(response);
    }
}