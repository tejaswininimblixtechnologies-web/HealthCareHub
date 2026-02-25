package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/{doctorId}/prescriptions")
    public ResponseEntity<String> addPrescription(
            @PathVariable Long doctorId,
            @RequestBody List<String> medicines) {

        return ResponseEntity.ok(
                doctorService.addPrescription(doctorId, medicines)
        );
    }

    @GetMapping("/{doctorId}/prescriptions")
    public ResponseEntity<List<String>> getPrescriptions(
            @PathVariable Long doctorId) {

        return ResponseEntity.ok(
                doctorService.getPrescriptions(doctorId)
        );
    }
}