package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.PatientRegistrationRequest;
import nimblix.in.HealthCareHub.response.PatientResponse;

public interface PatientService {

    PatientResponse registerPatient(PatientRegistrationRequest request);

}