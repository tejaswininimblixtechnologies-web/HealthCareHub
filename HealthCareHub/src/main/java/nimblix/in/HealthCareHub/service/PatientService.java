package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.dto.FeedbackDto;
import nimblix.in.HealthCareHub.dto.PatientDto;
import nimblix.in.HealthCareHub.model.Appointment;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientService {

    String registerPatient(PatientDto dto);

    ResponseEntity<Resource> downloadMedicalReport(Long patientId);

    List<Appointment> getUpcomingAppointments(Long patientId);

    String updateProfile(Long patientId, PatientDto dto);

    String submitFeedback(FeedbackDto dto);
}


















