package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findByPatientId(Long patientId);
}