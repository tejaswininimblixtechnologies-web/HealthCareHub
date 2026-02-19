package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    List<Appointment> findByPatient_Id(Long patientId);


    List<Appointment> findByPatient_IdAndStatus(Long patientId, String status);
}


