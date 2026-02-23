package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Patient;

import java.util.Optional;

public interface PatientService {

    // ✅ Save Patient
    Patient savePatient(Patient patient);

    // ✅ Get Patient By ID
    Optional<Patient> getPatientById(Long id);
}