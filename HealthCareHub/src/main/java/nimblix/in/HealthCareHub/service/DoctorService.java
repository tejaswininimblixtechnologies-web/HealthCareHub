package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.dto.DoctorRegisterRequest;
import nimblix.in.HealthCareHub.model.Doctor;

public interface DoctorService {

    Doctor registerDoctor(DoctorRegisterRequest request);
}
