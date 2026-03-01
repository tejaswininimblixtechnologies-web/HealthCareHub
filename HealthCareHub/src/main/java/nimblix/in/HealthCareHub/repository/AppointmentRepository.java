package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Single date
    List<Appointment> findByAppointmentDate(String appointmentDate);

    // Date range
    List<Appointment> findByAppointmentDateBetween(
            String startDate,
            String endDate
    );
}