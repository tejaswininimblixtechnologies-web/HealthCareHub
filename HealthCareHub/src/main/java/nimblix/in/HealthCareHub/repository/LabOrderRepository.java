package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.LabOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabOrderRepository extends JpaRepository<LabOrder, Long> {
   List<LabOrder> findByPatientId(Long patientId);
}
