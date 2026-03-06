package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.AdmitPatientRequest;
import nimblix.in.HealthCareHub.request.PatientRegistrationRequest;
import nimblix.in.HealthCareHub.response.AdmitPatientResponse;
import nimblix.in.HealthCareHub.response.LabResultResponse;
import nimblix.in.HealthCareHub.service.AdmissionService;
import nimblix.in.HealthCareHub.service.LabResultService;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final AdmissionService admissionService;
    private final LabResultService labResultService;

    // -------------------- Patient Registration --------------------

    @PostMapping("/register")
    public ResponseEntity<?> registerPatient(@RequestBody PatientRegistrationRequest request) {

        if (request == null) {
            return ResponseEntity.badRequest().body("Request body cannot be empty");
        }

        String response = patientService.registerPatient(request);
        return ResponseEntity.ok(response);
    }

    // -------------------- Get Patient Details --------------------

    @GetMapping("/{patientId}/{hospitalId}")
    public ResponseEntity<?> getPatientDetails(@PathVariable Long patientId,
                                               @PathVariable Long hospitalId) {

        return patientService.getPatientDetails(patientId, hospitalId);
    }

    // -------------------- Update Patient --------------------

    @PutMapping
    public ResponseEntity<?> updatePatientDetails(@RequestBody PatientRegistrationRequest request) {

        if (request == null || request.getPatientId() == null) {
            return ResponseEntity.badRequest().body("Patient ID is required for update");
        }

        return ResponseEntity.ok(patientService.updatePatientDetails(request));
    }

    // -------------------- Delete Patient --------------------

    @DeleteMapping("/{patientId}")
    public ResponseEntity<?> deletePatientDetails(@PathVariable Long patientId) {

        return ResponseEntity.ok(patientService.deletePatientDetails(patientId));
    }

    // -------------------- Admit Patient --------------------

    @PostMapping("/admissions/admit")
    public ResponseEntity<Map<String, Object>> admitPatient(
            @RequestBody AdmitPatientRequest request) {

        AdmitPatientResponse data = admissionService.admitPatient(request);

        Map<String, Object> response = new HashMap<>();
        response.put("message",
                data == null ? "Patient or Doctor not found" : "Patient admitted successfully");
        response.put("data", data);

        return ResponseEntity.ok(response); // always 200 OK
    }

    // -------------------- Lab Results --------------------

    @GetMapping("/lab-results/patient/{patientId}")
    public ResponseEntity<Map<String, Object>> getLabResultsByPatient(
            @PathVariable Long patientId) {

        List<LabResultResponse> data = labResultService.getLabResultsByPatient(patientId);

        Map<String, Object> response = new HashMap<>();
        response.put("message",
                data == null ? "Patient not found" : "Lab results fetched successfully");
        response.put("count", data == null ? 0 : data.size());
        response.put("data", data);

        return ResponseEntity.ok(response); // always 200 OK
    }
}