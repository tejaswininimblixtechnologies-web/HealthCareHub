package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // Activate Patient
    @PutMapping("/{userId}/activate")
    public ResponseEntity<Map<String, Object>> activatePatient(@PathVariable Long userId) {

        Map<String, Object> response = new HashMap<>();

        try {

            patientService.activatePatient(userId);

            Map<String, Object> data = new HashMap<>();
            data.put("success", true);

            response.put(HealthCareConstants.STATUS, HttpStatus.OK.value());
            response.put(HealthCareConstants.MESSAGE, HealthCareConstants.PATIENT_ACTIVATED_SUCCESSFULLY);
            response.put(HealthCareConstants.DATA, data);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            response.put(HealthCareConstants.STATUS, HttpStatus.BAD_REQUEST.value());
            response.put(HealthCareConstants.MESSAGE, HealthCareConstants.PATIENT_ACTIVATION_FAILED);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // Deactivate Patient
    @PutMapping("/{userId}/deactivate")
    public ResponseEntity<Map<String, Object>> deactivatePatient(@PathVariable Long userId) {

        Map<String, Object> response = new HashMap<>();

        try {

            patientService.deactivatePatient(userId);

            Map<String, Object> data = new HashMap<>();
            data.put("success", true);

            response.put(HealthCareConstants.STATUS, HttpStatus.OK.value());
            response.put(HealthCareConstants.MESSAGE, "Patient deactivated successfully");
            response.put(HealthCareConstants.DATA, data);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            response.put(HealthCareConstants.STATUS, HttpStatus.BAD_REQUEST.value());
            response.put(HealthCareConstants.MESSAGE, "Patient deactivation failed");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}