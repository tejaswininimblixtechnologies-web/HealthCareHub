package nimblix.in.HealthCareHub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.service.DoctorService;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {


    public final PatientService patientService;

    @PutMapping("/update/{patientId}")
    public ResponseEntity<?> updatePatient(
            @PathVariable Long patientId,
            @Valid @RequestBody Patient request) {

        return patientService.updatePatient(patientId, request);
    }

}
