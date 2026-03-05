package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.response.PatientSearchResponse;

import java.util.List;

public interface PatientService {

    List<PatientSearchResponse> searchPatients(String name, String phone, String bloodGroup);

}