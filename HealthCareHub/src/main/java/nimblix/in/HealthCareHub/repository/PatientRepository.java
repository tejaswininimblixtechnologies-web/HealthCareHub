package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.model.patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<patient, Long> {
    // Find all prescriptions for a specific patient
    //List<Prescription> findByPatientId(Long patientId);

    // Find prescriptions ordered by date (newest first)
    //List<Prescription> findByPatient_IdOrderByPrescriptionDateDesc(Long patientId);

}
