package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.AdmissionRequest;
import nimblix.in.HealthCareHub.request.PatientRequest;
import nimblix.in.HealthCareHub.response.AdmissionResponse;
import nimblix.in.HealthCareHub.response.PatientResponse;

import java.util.List;

public interface PatientService {

    PatientResponse addPatient(PatientRequest request);

    List<PatientResponse> getAllPatients();

    PatientResponse getPatientById(Long id);

    PatientResponse updatePatient(Long id, PatientRequest request);

    void deletePatient(Long id);

    public AdmissionResponse createAdmission(Long patientId, AdmissionRequest request);

    List<AdmissionResponse> getAdmissionHistory(Long patientId);

}
