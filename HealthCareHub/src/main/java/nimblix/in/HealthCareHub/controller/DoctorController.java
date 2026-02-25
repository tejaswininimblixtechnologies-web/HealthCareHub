package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.service.AppointmentService;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    // 🔹 Get All Doctors
    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    // 🔹 Create Doctor
    @PostMapping("/doctors")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.saveDoctor(doctor));
    }

    // 🔹 OLD Reschedule API (Works Like Before)
    @PutMapping("/appointments/reschedule/{appointmentId}")
    public ResponseEntity<Appointment> rescheduleAppointment(
            @PathVariable Long appointmentId,
            @RequestParam LocalDate newDate,
            @RequestParam LocalTime newStart,
            @RequestParam LocalTime newEnd) {

        // Combine date + start time
        LocalDateTime newDateTime = LocalDateTime.of(newDate, newStart);

        Appointment updated =
                appointmentService.rescheduleAppointment(
                        appointmentId,
                        newDateTime
                );

        return ResponseEntity.ok(updated);
    }
}