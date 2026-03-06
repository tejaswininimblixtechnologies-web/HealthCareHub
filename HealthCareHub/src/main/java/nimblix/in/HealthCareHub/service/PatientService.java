package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.PatientRegistrationRequest;
import org.springframework.http.ResponseEntity;

public interface PatientService {

    String registerPatient(PatientRegistrationRequest request);

    ResponseEntity<?> getPatientDetails(Long patientId, Long hospitalId);

    String updatePatientDetails(PatientRegistrationRequest request);

    String deletePatientDetails(Long patientId);
}