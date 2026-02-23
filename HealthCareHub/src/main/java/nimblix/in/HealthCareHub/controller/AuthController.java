package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.dto.DoctorRegisterRequest;
import nimblix.in.HealthCareHub.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    // DOCTOR REGISTER API
    @PostMapping("/doctor/register")
    public String registerDoctor(
            @RequestBody DoctorRegisterRequest request) {

        return authService.registerDoctor(request);
    }
}
