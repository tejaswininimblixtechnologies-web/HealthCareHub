package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Patient;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientService {

    Patient registerPatient(Patient patient);

    Patient updatePatient(Long id, Patient patient);

    Patient getPatient(Long id);

    ResponseEntity<Resource> downloadMedicalReport(Long id);

    List<String> getAppointments(Long id);
}
