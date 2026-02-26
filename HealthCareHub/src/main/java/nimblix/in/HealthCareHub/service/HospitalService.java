package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.response.RevenueSummaryResponse;

public interface HospitalService {

    String registerHospital(HospitalRegistrationRequest request);


    RevenueSummaryResponse getRevenueSummary();
}
