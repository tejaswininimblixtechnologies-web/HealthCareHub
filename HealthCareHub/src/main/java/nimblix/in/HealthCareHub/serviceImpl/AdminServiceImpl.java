package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.request.AssignPermissionRequest;
import nimblix.in.HealthCareHub.response.PermissionResponse;
import nimblix.in.HealthCareHub.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Override
    public String assignPermissions(AssignPermissionRequest request) {

        Role role = request.getRole();
        Set<String> permissions = request.getPermissions();

        if (role == null) {
            throw new RuntimeException("Role cannot be null");
        }

        if (permissions == null || permissions.isEmpty()) {
            throw new RuntimeException("Permissions cannot be empty");
        }

        String email = role.name().toLowerCase() + "@system.com";

        Optional<User> optionalUser = userRepository.findByEmail(email);

        User user;

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            user = new User();
            user.setEmail(email);
            user.setPassword("system");
            user.setRole(role);
            user.setEnabled(true);
        }

        user.setPermissions(new HashSet<>(permissions));
        userRepository.save(user);

        return "Permissions assigned successfully";
    }

    @Override
    public PermissionResponse getPermissions(Role role) {

        String email = role.name().toLowerCase() + "@system.com";

        Optional<User> optionalUser = userRepository.findByEmail(email);

        Set<String> permissions = optionalUser
                .map(User::getPermissions)
                .orElse(new HashSet<>());

        return new PermissionResponse(
                "Permissions fetched successfully",
                role,
                permissions
        );
    }
}