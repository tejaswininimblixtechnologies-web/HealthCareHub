package nimblix.in.HealthCareHub.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @GetMapping("/dashboard")
    public String patientDashboard() {
        return "Welcome Patient";
    }

    @PostMapping("/book-appointment")
    public String bookAppointment() {
        return "Appointment booked successfully";
    }

    @GetMapping("/reports")
    public String viewReports() {
        return "Patient medical reports";
    }
}
