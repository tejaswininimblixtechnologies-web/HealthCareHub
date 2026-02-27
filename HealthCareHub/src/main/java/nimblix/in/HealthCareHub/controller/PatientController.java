package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.response.ApiResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PutMapping("/{patientId}/activate")
    public ResponseEntity<ApiResponse> activatePatient(@PathVariable Long patientId) {
        return patientService.activatePatient(patientId);
    }

    @PutMapping("/{patientId}/deactivate")
    public ResponseEntity<ApiResponse> deactivatePatient(@PathVariable Long patientId) {
        return patientService.deactivatePatient(patientId);
    }
}