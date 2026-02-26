package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.service.UserService;
import nimblix.in.HealthCareHub.serviceImpl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    // ===================== USER APIs =====================

    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    // (Optional) get user by id
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // ===================== PATIENT APIs =====================

    @PostMapping("/add")
    public Patient addPatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    // Get all non-deleted patients
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    // Soft delete patient by ID
    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Long id) {
        return patientService.softDeletePatient(id);
    }
}
