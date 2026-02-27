package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>
{

    List<Attendance> findByStaffId(Long staffId);

    List<Attendance> findByDate(LocalDate date);
}
