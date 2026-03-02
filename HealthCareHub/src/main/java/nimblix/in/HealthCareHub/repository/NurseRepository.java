package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NurseRepository extends JpaRepository<Nurse, Long> {
    
    List<Nurse> findByHospitalId(Long hospitalId);
    
    List<Nurse> findByNameContainingIgnoreCase(String name);
    
    List<Nurse> findByDepartment(String department);
}
