package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.request.LoginRequest;
import nimblix.in.HealthCareHub.response.LoginResponse;
import nimblix.in.HealthCareHub.security.JwtUtil;
import nimblix.in.HealthCareHub.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest request) {

        // Authenticate user (Spring Security)
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Get authenticated user
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Generate JWT Token
        String token = jwtUtil.generateToken(userDetails);

        // Fetch user from DB
        User user = userRepository.findByEmail(request.getEmail().toLowerCase())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Prepare response
        return new LoginResponse(
                token,
                user.getId(),
                user.getEmail(),   // name (no name column)
                user.getEmail(),
                user.getRole().name()
        );
    }
}