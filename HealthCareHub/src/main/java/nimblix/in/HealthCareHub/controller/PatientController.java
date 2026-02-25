package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.PatientSearchRequest;
import nimblix.in.HealthCareHub.response.PatientResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patient")   // keep singular as in your base code
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;  // injected via Lombok

    /**
     * Search patients with optional filters: name, phone, blood group
     * POST request because filters are sent in the body
     */
    @PostMapping("/search")
    public ResponseEntity<List<PatientResponse>> searchPatients(
            @RequestBody PatientSearchRequest request) {

        List<PatientResponse> result = patientService.searchPatients(request);
        return ResponseEntity.ok(result);
    }
}