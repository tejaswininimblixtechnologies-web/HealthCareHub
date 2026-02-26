package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;

import nimblix.in.HealthCareHub.model.Appointment;

import nimblix.in.HealthCareHub.service.AppointmentService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("api/appointments")

@RequiredArgsConstructor

public class AppointmentController {
    

    private final AppointmentService appointmentService;

    // Book Appointment API

    @PostMapping("/book")

    public ResponseEntity<?> bookAppointment(@RequestBody Appointment appointment) {

        Appointment savedAppointment = appointmentService.bookAppointment(appointment);

        return ResponseEntity.ok(savedAppointment);

    }

}
