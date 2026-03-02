package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping("/register")
    public ResponseEntity<?> registerHospital(@RequestBody HospitalRegistrationRequest request) {

        if (request == null) {
            return ResponseEntity.badRequest().body("Request body cannot be empty");
        }

        return ResponseEntity.ok(hospitalService.registerHospital(request));
    }

    @PostMapping("/staff")
    public ResponseEntity<?> addStaff(@RequestBody User user) {

        if (user == null) {
            return ResponseEntity.badRequest().body("Request body cannot be empty");
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("Email is required");
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body("Password is required");
        }

        if (user.getRole() == null) {
            return ResponseEntity.badRequest().body("Role is required");
        }

        try {
            User savedUser = hospitalService.addStaff(user);
            return ResponseEntity.ok(savedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/staff")
    public ResponseEntity<?> getAllStaff() {

        List<User> staffList = hospitalService.getAllStaff();

        if (staffList.isEmpty()) {
            return ResponseEntity.ok("No staff found");
        }

        return ResponseEntity.ok(staffList);
    }
}
