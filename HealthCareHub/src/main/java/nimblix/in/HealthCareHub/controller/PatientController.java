package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.response.ApiResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PutMapping("/{userId}/activate")
    public ResponseEntity<ApiResponse> activatePatient(
            @PathVariable("userId") Long userId){
        return patientService.activatePatient(userId);
    }

    @PutMapping("/{userId}/deactivate")
    public ResponseEntity<ApiResponse> deactivatePatient(
            @PathVariable("userId") Long userId){
        return patientService.deactivatePatient(userId);
    }
}