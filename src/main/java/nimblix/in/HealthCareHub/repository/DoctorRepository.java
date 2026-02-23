package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    List<Doctor> findByNameContainingIgnoreCase(String name);
    List<Doctor> findBySpecializationId(Long specializationId);
    List<Doctor> findByNameContainingIgnoreCaseAndSpecializationId(String name, Long specializationId);
}
