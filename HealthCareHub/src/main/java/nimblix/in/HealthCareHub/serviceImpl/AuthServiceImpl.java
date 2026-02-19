package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.BlacklistedToken;
import nimblix.in.HealthCareHub.repository.BlacklistedTokenRepository;
import nimblix.in.HealthCareHub.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private BlacklistedTokenRepository blacklistedTokenRepository;

    @Override
    public void logout(String token) {
        // Save token to blacklist
        BlacklistedToken blacklistedToken = new BlacklistedToken(token);
        blacklistedTokenRepository.save(blacklistedToken);

        // Clear security context
        SecurityContextHolder.clearContext();
    }
}
