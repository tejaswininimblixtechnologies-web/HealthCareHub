package nimblix.in.HealthCareHub.controller;


import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String>register(@RequestBody User user){
           //--Check duplicate email--
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("Email already exists");
        }
       //--Encode password--
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //--role--
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        //---save user--
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");

    }
}
