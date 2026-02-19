package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // ✅ Task 1 – Patient Registration
    @PostMapping("/register")
    public Patient register(@RequestBody Patient patient) {
        return patientService.registerPatient(patient);
    }

    // ✅ Task 2 – Download Medical Report
    @GetMapping("/report/{id}")
    public ResponseEntity<Resource> downloadReport(@PathVariable Long id) {
        return patientService.downloadMedicalReport(id);
    }

    // ✅ Task 3 – Upcoming Appointments
    @GetMapping("/appointments/{id}")
    public List<String> getAppointments(@PathVariable Long id) {
        return patientService.getAppointments(id);
    }

    // ✅ Task 4 – Update Profile
    @PutMapping("/update/{id}")
    public Patient update(@PathVariable Long id, @RequestBody Patient patient) {
        return patientService.updatePatient(id, patient);
    }

    // ✅ Test API (to confirm controller working)
    @GetMapping("/test")
    public String test() {
        return "Patient Controller Working ✅";
    }
}
