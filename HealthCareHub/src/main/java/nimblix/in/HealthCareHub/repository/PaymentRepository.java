package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
