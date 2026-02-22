package nimblix.in.HealthCareHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nimblix.in.HealthCareHub.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}
