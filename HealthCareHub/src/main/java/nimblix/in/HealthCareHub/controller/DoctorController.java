package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

//    @PostMapping("/register")
//    public String registerDoctor(@RequestBody DoctorRegistrationRequest request) {
//        return doctorService.registerDoctor(request);
//
//    }
@GetMapping("/profile")
public ResponseEntity<?> getUserProfile() {
    return ResponseEntity.ok(
            doctorService.getUserProfile());
}
}
