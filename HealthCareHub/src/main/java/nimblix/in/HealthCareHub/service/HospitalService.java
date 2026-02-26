package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.response.HospitalResponse;

import java.util.List;

public interface HospitalService {

    HospitalResponse createHospital(HospitalRegistrationRequest request);

    List<HospitalResponse> getAllHospitals();
}