package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.InsuranceClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClaimRepository extends JpaRepository<InsuranceClaim, Long> {
    List<InsuranceClaim> findByPatientId(Long patientId);
}