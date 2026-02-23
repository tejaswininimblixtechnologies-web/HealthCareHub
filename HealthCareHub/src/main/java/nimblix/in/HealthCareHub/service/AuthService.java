package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.dto.DoctorRegisterRequest;

public interface AuthService {

    String registerDoctor(DoctorRegisterRequest request);
}