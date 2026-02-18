package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // enable user
        user.setEnabled(true);

        userRepository.save(user);
        return "User Registered Successfully";
    }

    // LOGIN USER
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        // find user by email (NOT username)
        User dbUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // check password
        if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        // generate token using email
        return jwtUtil.generateToken(dbUser.getEmail());
    }
}
