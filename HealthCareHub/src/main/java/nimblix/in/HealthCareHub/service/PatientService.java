package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.AdmissionRequest;
import nimblix.in.HealthCareHub.response.AdmissionResponse;

import java.util.List;

public interface PatientService {

    List<AdmissionResponse> getAdmissionHistory(AdmissionRequest request);

}