package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.request.PatientSearchRequest;
import nimblix.in.HealthCareHub.response.PatientResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // POST search endpoint
    @PostMapping("/search")
    public ResponseEntity<List<PatientResponse>> searchPatients(@RequestBody PatientSearchRequest request) {

        List<PatientResponse> patients = patientService.searchPatients(
                request.getName(),
                request.getBloodGroup(),
                request.getPhone()
        );

        return ResponseEntity.ok(patients);
    }
}