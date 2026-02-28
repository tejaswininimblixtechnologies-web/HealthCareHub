package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import org.springframework.data.domain.Page;
import nimblix.in.HealthCareHub.model.Medicine;

public interface HospitalService {

    String registerHospital(HospitalRegistrationRequest request);

}
