package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Search by name, blood group, or phone (case-insensitive, partial match)
    List<Patient> findByNameContainingIgnoreCaseOrBloodGroupContainingIgnoreCaseOrPhoneContainingIgnoreCase(
            String name, String bloodGroup, String phone
    );
}