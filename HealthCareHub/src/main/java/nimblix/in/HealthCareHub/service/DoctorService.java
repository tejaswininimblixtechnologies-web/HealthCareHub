package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import org.springframework.http.ResponseEntity;

public interface DoctorService {
    String RegisterDoctor(DoctorRegistrationRequest doctorRegistrationRequest);

    String registerDoctor(DoctorRegistrationRequest doctorRegistrationRequest);
}
