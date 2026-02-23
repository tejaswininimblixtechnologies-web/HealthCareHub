package nimblix.in.HealthCareHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nimblix.in.HealthCareHub.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
}