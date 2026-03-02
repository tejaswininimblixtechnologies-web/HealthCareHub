package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.PatientRequest;
import nimblix.in.HealthCareHub.response.PatientResponse;

import java.util.List;

public interface PatientService {
    
    String registerPatient(PatientRequest request);
    
    List<PatientResponse> getAllPatients(Long hospitalId, String name);
    
    PatientResponse getPatientById(Long id);
}
