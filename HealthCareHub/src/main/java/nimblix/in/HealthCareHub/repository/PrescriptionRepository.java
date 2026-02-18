package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    // Custom query method to find all prescriptions by patient ID
    List<Prescription> findByPatient_IdOrderByPrescriptionDateDesc(Long patientId);


}
