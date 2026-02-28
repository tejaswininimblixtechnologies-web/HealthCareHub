package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.response.FeedbackDto;
import nimblix.in.HealthCareHub.response.PatientDto;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // 🔹 Task 56 — Patient Registration
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody PatientDto dto) {
        return ResponseEntity.ok(patientService.registerPatient(dto));
    }

    // 🔹 Task 57 — Download Medical Report
    @GetMapping("/report/{patientId}")
    public ResponseEntity<Resource> downloadReport(@PathVariable Long patientId) {
        return patientService.downloadMedicalReport(patientId);
    }

    // 🔹 Task 58 — Upcoming Appointments
    @GetMapping("/{patientId}/appointments")
    public ResponseEntity<?> upcomingAppointments(@PathVariable Long patientId) {
        return ResponseEntity.ok(
                patientService.getUpcomingAppointments(patientId));
    }

    // 🔹 Task 59 — Update Profile
    @PutMapping("/update/{patientId}")
    public ResponseEntity<?> updateProfile(
            @PathVariable Long patientId,
            @RequestBody PatientDto dto) {

        return ResponseEntity.ok(
                patientService.updateProfile(patientId, dto));
    }

    // 🔹 Task 60 — Feedback
    @PostMapping("/feedback")
    public ResponseEntity<?> submitFeedback(@RequestBody FeedbackDto dto) {
        return ResponseEntity.ok(patientService.submitFeedback(dto));
    }
}