package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface DoctorService {
    String registerDoctor(DoctorRegistrationRequest request);

    ResponseEntity<?> getDoctorDetails(Long doctorId, Long hospitalId);

    ResponseEntity<ApiResponse> activateDoctor(Long doctorId);
    ResponseEntity<ApiResponse> deactivateDoctor(Long doctorId);
}