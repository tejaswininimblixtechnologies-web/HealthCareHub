package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Optional<Doctor> findByEmailId(String emailId);
    
    List<Doctor> findBySpecializationId(Long specializationId);
    
    List<Doctor> findByHospitalId(Long hospitalId);
    
    List<Doctor> findByHospitalCity(String city);
    
    List<Doctor> findByNameContainingIgnoreCase(String name);
}
