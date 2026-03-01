package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.response.AppointmentResponse;
import nimblix.in.HealthCareHub.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final AppointmentService appointmentService;

    @GetMapping("/appointments/date-range")
    public ResponseEntity<List<AppointmentResponse>> getAppointments(
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {

        return ResponseEntity.ok(
                appointmentService.getAppointments(date, startDate, endDate));
    }
}