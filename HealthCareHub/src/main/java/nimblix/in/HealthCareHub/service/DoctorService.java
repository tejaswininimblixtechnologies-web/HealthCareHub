package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.dto.DoctorRegistrationRequest;

public interface DoctorService {
    String registerDoctor(DoctorRegistrationRequest request);
}