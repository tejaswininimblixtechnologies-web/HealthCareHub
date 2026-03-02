package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.PatientRegistrationRequest;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity<?> registerPatient(@RequestBody PatientRegistrationRequest request) {

        if (request == null) {
            return ResponseEntity.badRequest().body("Request body cannot be empty");
        }

        String response = patientService.registerPatient(request);

        if (response.contains("already exists")) {
            return ResponseEntity.status(409).body(response); // CONFLICT
        }

        return ResponseEntity.status(201).body(response); // CREATED
    }

    @GetMapping("/getPatientDetails/{patientId}/{hospitalId}")
    public ResponseEntity<?> getPatientDetails(@PathVariable Long patientId,
                                               @PathVariable Long hospitalId) {

        if (patientId == null || hospitalId == null) {
            return ResponseEntity.badRequest().body("PatientId and HospitalId are required");
        }

        return patientService.getPatientDetails(patientId, hospitalId);
    }

    @PutMapping("/updatePatientDetails")
    public ResponseEntity<?> updatePatientDetails(@RequestBody PatientRegistrationRequest request) {

        if (request == null || request.getPatientId() == null) {
            return ResponseEntity.badRequest().body("Patient ID is required for update");
        }

        return ResponseEntity.ok(patientService.updatePatientDetails(request));
    }

    @DeleteMapping("/deletePatientDetails")
    public ResponseEntity<?> deletePatientDetails(@RequestParam Long patientId) {

        if (patientId == null) {
            return ResponseEntity.badRequest().body("Patient ID is required");
        }

        return ResponseEntity.ok(patientService.deletePatientDetails(patientId));
    }
}