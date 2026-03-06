package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // Activate Doctor
    @PutMapping("/{doctorId}/activate")
    public ResponseEntity<Map<String, Object>> activateDoctor(@PathVariable Long doctorId) {

        Map<String, Object> response = new HashMap<>();

        try {

            doctorService.activateDoctor(doctorId);

            Map<String, Object> data = new HashMap<>();
            data.put("success", true);

            response.put(HealthCareConstants.STATUS, HttpStatus.OK.value());
            response.put(HealthCareConstants.MESSAGE, HealthCareConstants.DOCTOR_ACTIVATED_SUCCESSFULLY);
            response.put(HealthCareConstants.DATA, data);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            response.put(HealthCareConstants.STATUS, HttpStatus.BAD_REQUEST.value());
            response.put(HealthCareConstants.MESSAGE,  HealthCareConstants.DOCTOR_ACTIVATION_FAILED);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // Deactivate Doctor
    @PutMapping("/{doctorId}/deactivate")
    public ResponseEntity<Map<String, Object>> deactivateDoctor(@PathVariable Long doctorId) {

        Map<String, Object> response = new HashMap<>();

        try {

            doctorService.deactivateDoctor(doctorId);

            Map<String, Object> data = new HashMap<>();
            data.put("success", true);

            response.put(HealthCareConstants.STATUS, HttpStatus.OK.value());
            response.put(HealthCareConstants.MESSAGE, "Doctor deactivated successfully");
            response.put(HealthCareConstants.DATA, data);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            response.put(HealthCareConstants.STATUS, HttpStatus.BAD_REQUEST.value());
            response.put(HealthCareConstants.MESSAGE, "Doctor deactivation failed");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}