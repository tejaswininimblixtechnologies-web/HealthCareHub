package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @PutMapping("/appointments/reschedule/{id}")
    public ResponseEntity<Appointment> rescheduleAppointment(
            @PathVariable Long id,
            @RequestParam String newDateTime) {

        LocalDateTime dateTime = LocalDateTime.parse(newDateTime);

        Appointment updated =
                doctorService.rescheduleAppointment(id, dateTime);

        return ResponseEntity.ok(updated);
    }
}
