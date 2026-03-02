package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.request.AdmissionRequest;
import nimblix.in.HealthCareHub.response.AdmissionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nimblix.in.HealthCareHub.request.PatientRequest;
import nimblix.in.HealthCareHub.response.PatientResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;


    // Add Patient
    @PostMapping
    public ResponseEntity<PatientResponse> addPatient(@RequestBody PatientRequest request) {
        return ResponseEntity.ok(patientService.addPatient(request));
    }

    // Get All Patients
    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }
    @GetMapping ("/{id}")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> updatePatient(
            @PathVariable Long id,
            @RequestBody PatientRequest request) {

        return ResponseEntity.ok(patientService.updatePatient(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/admissions")
    public ResponseEntity<AdmissionResponse> createAdmission(
            @PathVariable Long id,
            @RequestBody AdmissionRequest request) {

        return ResponseEntity.ok(patientService.createAdmission(id, request));
    }
    // 🔥 Admission History API
    @GetMapping("/{id}/admissions")
    public ResponseEntity<List<AdmissionResponse>> getAdmissionHistory(
            @PathVariable Long id) {

        return ResponseEntity.ok(patientService.getAdmissionHistory(id));
    }


}