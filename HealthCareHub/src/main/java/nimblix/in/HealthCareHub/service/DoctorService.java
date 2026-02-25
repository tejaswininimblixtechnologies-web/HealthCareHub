package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DoctorService {
    String registerDoctor(DoctorRegistrationRequest request);

    Doctor getDoctorDetails(Long doctorId, Long hospitalId);

    String addPrescription(Long doctorId, List<String> medicines);

    List<String> getPrescriptions(Long doctorId);
}



