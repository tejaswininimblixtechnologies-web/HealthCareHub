package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    // Filter booked appointments
    @GetMapping("/filter")
    public ResponseEntity<List<Appointment>> filterAppointments(
            @RequestParam("doctorId") Long doctorId,
            @RequestParam("date") LocalDate date,
            @RequestParam("status") String status) {

        List<Appointment> appointments =
                appointmentService.filterAppointments(doctorId, date, status);

        return ResponseEntity.ok(appointments);
    }

    // Get available slots
    @GetMapping("/doctor/{doctorId}/available-slots")
    public ResponseEntity<List<LocalTime>> getAvailableSlots(
            @PathVariable("doctorId") Long doctorId,
            @RequestParam("date") LocalDate date) {

        List<LocalTime> slots =
                appointmentService.getAvailableSlots(doctorId, date);

        return ResponseEntity.ok(slots);
    }
}