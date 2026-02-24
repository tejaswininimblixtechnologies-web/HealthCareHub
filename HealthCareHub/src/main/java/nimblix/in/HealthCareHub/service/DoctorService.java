package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.request.DoctorSearchRequest;
import nimblix.in.HealthCareHub.response.DoctorResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DoctorService {
    String registerDoctor(DoctorRegistrationRequest request);

    ResponseEntity<?> getDoctorDetails(Long doctorId, Long hospitalId);

    List<DoctorResponse> getAllDoctors(DoctorSearchRequest request);
}
