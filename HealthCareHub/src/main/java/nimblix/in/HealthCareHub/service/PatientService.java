package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface PatientService {
    ResponseEntity<ApiResponse> activatePatient(Long patientId);
    ResponseEntity<ApiResponse> deactivatePatient(Long patientId);
}