package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    
    List<Patient> findByHospitalId(Long hospitalId);
    
    List<Patient> findByNameContainingIgnoreCase(String name);
}
