package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.response.PatientResponse;
import java.util.List;

public interface PatientService {

    List<PatientResponse> searchPatients(String name, String bloodGroup, String phone);
}