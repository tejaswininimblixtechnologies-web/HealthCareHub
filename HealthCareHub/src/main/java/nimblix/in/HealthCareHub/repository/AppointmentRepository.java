package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientIdOrderByAppointmentDateTimeDesc(Long patientId);
}