package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.UserRegistrationRequest;
import nimblix.in.HealthCareHub.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestBody UserRegistrationRequest request){
        return ResponseEntity.ok(userService.registerUser(request));
    }
}
