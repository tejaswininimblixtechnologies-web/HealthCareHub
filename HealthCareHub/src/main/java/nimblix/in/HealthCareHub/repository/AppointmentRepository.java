package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {

    // Find all appointments by patient ID
    List<Appointment> findByPatientId(Long patientId);

    // Find appointments by patient ID and status
    List<Appointment> findByPatientIdAndStatus(Long patientId, String status);
}