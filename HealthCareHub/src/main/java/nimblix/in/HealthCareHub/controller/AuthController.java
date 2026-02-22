package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // REGISTER USER
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        // 🔹 Check duplicate email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with this email");
        }

        // 🔹 Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 🔹 Enable user
        user.setEnabled(true);

        userRepository.save(user);

        return "User Registered Successfully";
    }

    // LOGIN USER
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {

        // 🔹 Find user by email
        User dbUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔹 Check password
        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        // 🔹 Generate JWT token
        String token = jwtUtil.generateToken(dbUser.getEmail());

        // 🔹 Return token as JSON
        return Map.of("token", token);
    }
}