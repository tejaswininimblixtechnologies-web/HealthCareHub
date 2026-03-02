package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Attendance;
import nimblix.in.HealthCareHub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Prevent duplicate attendance for same staff on same date
    Optional<Attendance> findByStaffAndDate(User user, LocalDate date);
}


