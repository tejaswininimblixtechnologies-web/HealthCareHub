package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PAtientRepository extends JpaRepository<Patient,Long> {
}
