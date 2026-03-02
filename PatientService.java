package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Patient;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    ResponseEntity<?> updatePatient(Long patientId, Patient request);

}
