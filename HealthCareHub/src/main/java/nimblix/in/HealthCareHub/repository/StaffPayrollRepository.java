package nimblix.in.HealthCareHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nimblix.in.HealthCareHub.model.StaffPayroll;

public interface StaffPayrollRepository
        extends JpaRepository<StaffPayroll, Long> {
}