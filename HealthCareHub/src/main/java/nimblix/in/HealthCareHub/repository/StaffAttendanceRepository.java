package nimblix.in.HealthCareHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nimblix.in.HealthCareHub.model.StaffAttendance;

public interface StaffAttendanceRepository
        extends JpaRepository<StaffAttendance, Long> {
}