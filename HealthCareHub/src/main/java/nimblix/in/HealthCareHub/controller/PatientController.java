package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import nimblix.in.HealthCareHub.request.PatientDocumentUploadRequest;
import nimblix.in.HealthCareHub.response.PatientDocumentUploadResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/patient")
@RequiredArgsConstructor
public class PatientController {
    @Autowired
    private PatientService patientService;


    @PostMapping(value = "/upload-document", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PatientDocumentUploadResponse> uploadPatientDocument(
            @RequestParam("patientId") Long patientId,
            @RequestParam("documentType") String documentType,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("file") MultipartFile file) {

        PatientDocumentUploadRequest request = new PatientDocumentUploadRequest();
        request.setPatientId(patientId);
        request.setDocumentType(documentType);
        request.setDescription(description);
        request.setFile(file);

        PatientDocumentUploadResponse response = patientService.uploadPatientDocument(request);

        return ResponseEntity.ok(response);
    }
}
