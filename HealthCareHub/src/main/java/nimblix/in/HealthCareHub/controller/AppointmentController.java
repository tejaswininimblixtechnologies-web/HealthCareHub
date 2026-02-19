package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    // Task 3 — View Upcoming Appointments
    @GetMapping("/patient/{patientId}")
    public List<Appointment> getUpcoming(@PathVariable Long patientId) {
        return appointmentService.getUpcomingAppointments(patientId);
    }
}

