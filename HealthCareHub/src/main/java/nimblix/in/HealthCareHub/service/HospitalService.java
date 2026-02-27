package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.response.HospitalResponse;

public interface HospitalService {

    HospitalResponse registerHospital(HospitalRegistrationRequest request);
}