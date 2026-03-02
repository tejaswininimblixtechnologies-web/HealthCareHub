package nimblix.in.HealthCareHub.service;
import nimblix.in.HealthCareHub.request.PatientSearchRequest;
import nimblix.in.HealthCareHub.response.PatientResponse;

import java.util.List;

public interface PatientService {

    List<PatientResponse> searchPatients(PatientSearchRequest request);

    List<PatientResponse> searchPatients(String name, String phone, String bloodGroup);
}

