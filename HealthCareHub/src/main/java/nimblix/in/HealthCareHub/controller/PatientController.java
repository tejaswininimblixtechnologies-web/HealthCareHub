package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import nimblix.in.HealthCareHub.request.RescheduleAppointmentRequest;
import nimblix.in.HealthCareHub.response.AppointmentResponse;
import nimblix.in.HealthCareHub.service.PatientService;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    //  Reschedule Appointment
    @PutMapping("/{patientId}/appointments/{appointmentId}/reschedule")
    public ResponseEntity<AppointmentResponse> rescheduleAppointment(
            @PathVariable("patientId") Long patientId,
            @PathVariable("appointmentId") Long appointmentId,
            @RequestBody RescheduleAppointmentRequest request) {

        AppointmentResponse response =
                patientService.rescheduleAppointment(
                        patientId,
                        appointmentId,
                        request
                );

        return ResponseEntity.ok(response);
    }
}