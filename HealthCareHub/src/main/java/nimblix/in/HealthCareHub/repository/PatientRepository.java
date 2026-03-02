package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient, Long> {
}
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    // Get only active patients
//    List<Patient> findByIsDeletedFalse();

}
