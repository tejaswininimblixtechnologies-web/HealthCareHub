package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a WHERE a.status = 'BOOKED' AND a.appointmentDateTime BETWEEN :start AND :end")
    List<Appointment> findTomorrowAppointments(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);
}
