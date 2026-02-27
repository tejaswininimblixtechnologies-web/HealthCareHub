package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
