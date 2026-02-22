package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.model.DoctorAvailability;
import nimblix.in.HealthCareHub.service.DoctorService;
import nimblix.in.HealthCareHub.dto.DoctorRegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.createDoctor(doctor));
    }
    @PostMapping("/register")
    public ResponseEntity<Doctor> registerDoctor(
            @RequestBody DoctorRegistrationRequest request) {
        return ResponseEntity.ok(doctorService.registerDoctor(request));
    }
    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorService.getDoctorById(doctorId));
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Doctor>> getDoctorsByCity(
            @PathVariable String city) {
        return ResponseEntity.ok(
                doctorService.getDoctorsByCity(city));
    }

    @GetMapping("/specialization/{specializationId}")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialization(
            @PathVariable Long specializationId) {
        return ResponseEntity.ok(
                doctorService.getDoctorsBySpecialization(specializationId));
    }
    @GetMapping("/{doctorId}/availability")
    public ResponseEntity<List<DoctorAvailability>> getDoctorAvailability(
            @PathVariable Long doctorId) {
        return ResponseEntity.ok(
                doctorService.getDoctorAvailability(doctorId));
    }
    @PutMapping("/{doctorId}/availability")
    public ResponseEntity<Void> updateAvailability(
            @PathVariable Long doctorId,
            @RequestBody List<DoctorAvailability> availability) {

        doctorService.updateAvailability(doctorId, availability);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{doctorId}")
    public ResponseEntity<Doctor> updateDoctor(
            @PathVariable Long doctorId,
            @RequestBody Doctor doctor) {

        return ResponseEntity.ok(
                doctorService.updateDoctor(doctorId, doctor));
    }
    @PutMapping("/{doctorId}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long doctorId,
            @RequestParam String status) {

        doctorService.updateDoctorStatus(doctorId, status);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(
            @RequestParam String email) {

        return ResponseEntity.ok(
                doctorService.checkEmailExists(email));
    }

}
