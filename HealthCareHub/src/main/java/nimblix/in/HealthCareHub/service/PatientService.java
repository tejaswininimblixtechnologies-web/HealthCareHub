package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.response.PatientResponse;
import java.util.List;

public interface PatientService {

    // Search patients by GET or POST
    List<PatientResponse> searchPatients(String name, String bloodGroup, String phone);
}