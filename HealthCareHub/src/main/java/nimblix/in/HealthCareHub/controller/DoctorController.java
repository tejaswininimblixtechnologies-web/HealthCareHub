package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // Register Doctor
    @PostMapping("/register")
    public ResponseEntity<?> registerDoctor(
            @Validated @RequestBody DoctorRegistrationRequest request) {

        String response = doctorService.registerDoctor(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Doctor Details
    @GetMapping("/getDoctorDetails/{doctorId}/{hospitalId}")
    public ResponseEntity<?> getDoctorDetails(
            @PathVariable Long doctorId,
            @PathVariable Long hospitalId) {

        return doctorService.getDoctorDetails(doctorId, hospitalId);
    }

    // Update Doctor Details
    @PutMapping("/updateDoctorDetails")
    public ResponseEntity<?> updateDoctorDetails(
            @Validated @RequestBody DoctorRegistrationRequest request) {

        String response = doctorService.updateDoctorDetails(request);
        return ResponseEntity.ok(response);
    }

    // Delete Doctor
    @DeleteMapping("/deleteDoctorDetails")
    public ResponseEntity<?> deleteDoctorDetails(
            @RequestParam Long doctorId) {

        String response = doctorService.deleteDoctorDetails(doctorId);
        return ResponseEntity.ok(response);
    }
}