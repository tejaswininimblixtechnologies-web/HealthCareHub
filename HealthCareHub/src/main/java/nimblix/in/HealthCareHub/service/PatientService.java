package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.response.FeedbackDto;
import nimblix.in.HealthCareHub.response.PatientDto;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientService {

    // 🔹 Task 56 — Patient Self Registration
    String registerPatient(PatientDto dto);

    // 🔹 Task 57 — Download Medical Report
    ResponseEntity<Resource> downloadMedicalReport(Long patientId);

    // 🔹 Task 58 — View Upcoming Appointments
    List<String> getUpcomingAppointments(Long patientId);

    // 🔹 Task 59 — Update Profile
    PatientDto updateProfile(Long patientId, PatientDto dto);

    // 🔹 Task 60 — Feedback & Rating
    String submitFeedback(FeedbackDto dto);
}
