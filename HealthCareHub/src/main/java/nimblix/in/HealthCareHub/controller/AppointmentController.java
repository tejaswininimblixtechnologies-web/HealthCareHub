package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.response.AppointmentResponseDTO;
import nimblix.in.HealthCareHub.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // GET /api/appointments/patient/{patientId}
    // Task 170 - Get all appointments for a patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<Map<String, Object>> getAppointmentsByPatient(
            @PathVariable Long patientId) {

        List<AppointmentResponseDTO> data =
                appointmentService.getAppointmentsByPatient(patientId);

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Appointments fetched successfully");
        response.put("count", data.size());
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}