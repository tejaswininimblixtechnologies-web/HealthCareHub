package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.response.ApiResponse;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // Activate doctor
    @PutMapping("/{doctorId}/activate")
    public ResponseEntity<ApiResponse> activateDoctor(@PathVariable("doctorId") Long doctorId) {
        return doctorService.activateDoctor(doctorId);
    }

    // Deactivate doctor
    @PutMapping("/{doctorId}/deactivate")
    public ResponseEntity<ApiResponse> deactivateDoctor(@PathVariable("doctorId") Long doctorId) {
        return doctorService.deactivateDoctor(doctorId);
    }
}