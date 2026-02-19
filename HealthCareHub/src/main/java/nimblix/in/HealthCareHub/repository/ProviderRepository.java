package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.InsuranceProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<InsuranceProvider, Long> {
    // You can add custom queries here if needed
}