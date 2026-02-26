package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.response.DoctorResponse;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/register")
    public ResponseEntity<DoctorResponse> registerDoctor(
            @RequestBody DoctorRegistrationRequest request) {

        return ResponseEntity.ok(doctorService.registerDoctor(request));
    }

    @GetMapping("/{doctorId}/hospital/{hospitalId}")
    public ResponseEntity<DoctorResponse> getDoctorDetails(
            @PathVariable Long doctorId,
            @PathVariable Long hospitalId) {

        return ResponseEntity.ok(
                doctorService.getDoctorDetails(doctorId, hospitalId)
        );
    }
}