package nimblix.in.HealthCareHub.repository;

<<<<<<< Prajwal-S-R
import org.springframework.data.jpa.repository.JpaRepository;
import nimblix.in.HealthCareHub.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
=======
import nimblix.in.HealthCareHub.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    // Get only active patients
    List<Patient> findByIsDeletedFalse();

}
>>>>>>> main
