package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.service.AppointmentService;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    //  Register Doctor
    @PostMapping("/register")
    public String registerDoctor(@RequestBody DoctorRegistrationRequest request) {
        return doctorService.registerDoctor(request);
    }

    //  Get Doctor Details
    @GetMapping("/getDoctorDetails")
    public ResponseEntity<?> getDoctorDetails(@RequestParam Long doctorId,
                                              @RequestParam Long hospitalId) {
        return doctorService.getDoctorDetails(doctorId, hospitalId);
    }

    //  Reschedule Appointment
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