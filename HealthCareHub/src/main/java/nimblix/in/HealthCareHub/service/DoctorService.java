package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.dto.DoctorResponseDTO;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DoctorService {
    String registerDoctor(DoctorRegistrationRequest request);

    ResponseEntity<?> getDoctorDetails(Long doctorId, Long hospitalId);

    List<DoctorResponseDTO> searchDoctors(String name, Long specializationId, Long hospitalId);
}
