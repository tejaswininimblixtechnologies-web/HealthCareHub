package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;

import java.util.List;

public interface DoctorService {
    String RegisterDoctor(DoctorRegistrationRequest doctorRegistrationRequest);
    String registerDoctor(DoctorRegistrationRequest doctorRegistrationRequest);
    List<Doctor> getAllDoctors(String search, Long specializationId);
}
