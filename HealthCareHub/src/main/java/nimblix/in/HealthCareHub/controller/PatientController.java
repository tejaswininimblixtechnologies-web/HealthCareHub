package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.PatientRequest;
import nimblix.in.HealthCareHub.response.PatientResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity<String> registerPatient(@RequestBody PatientRequest request) {
        return ResponseEntity.ok(patientService.registerPatient(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<PatientResponse>> getAllPatients(
            @RequestParam(required = false) Long hospitalId,
            @RequestParam(required = false) String name) {
        return ResponseEntity.ok(patientService.getAllPatients(hospitalId, name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }
}
