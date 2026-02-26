package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // Register Doctor
    @PostMapping("/register")
    public ResponseEntity<String> registerDoctor(
            @RequestBody DoctorRegistrationRequest request) {

        String response = doctorService.registerDoctor(request);
        return ResponseEntity.ok(response);
    }

    // Get Doctor Details
    @GetMapping("/getDoctorDetails")
    public ResponseEntity<?> getDoctorDetails(
            @RequestParam Long doctorId,
            @RequestParam Long hospitalId) {

        return doctorService.getDoctorDetails(doctorId, hospitalId);
    }
}