package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.dto.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Override
    public String registerDoctor(DoctorRegistrationRequest request) {
        return "Doctor Registered: " + request.getName() +
                " | Specialization: " + request.getSpecialization();
    }
}
