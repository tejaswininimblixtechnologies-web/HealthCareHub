package nimblix.in.HealthCareHub.service;

import aj.org.objectweb.asm.commons.Remapper;
import jakarta.validation.Valid;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DoctorService {
    String registerDoctor(DoctorRegistrationRequest request);

    ResponseEntity<?> getDoctorDetails(Long doctorId, Long hospitalId);

    String updateDoctorDetails(DoctorRegistrationRequest request);

    String deleteDoctorDetails(Long doctorId);

    ResponseEntity<?> updateDoctor(Long doctorId, Doctor request);
}
