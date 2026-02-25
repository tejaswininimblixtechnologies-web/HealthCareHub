package nimblix.in.HealthCareHub.controller;


import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.serviceImpl.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patient")
@RequiredArgsConstructor
public class PatientController{
    private PatientService service;

    @PostMapping("/add")
    public Patient addPatient(@RequestBody Patient patient) {
        return service.savePatient(patient);
    }

    // Get all non-deleted patients
    @GetMapping("/patients")

    public List<Patient> getAllPatients() {

        return service.getAllPatients();
    }

    // Soft delete by ID
    @DeleteMapping("patients/{id}")
    public String deletePatient(@PathVariable Long id) {
        return service.softDeletePatient(id);
    }
}


