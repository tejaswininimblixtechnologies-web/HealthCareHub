package nimblix.in.HealthCareHub.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.request.UserStatusRequest;
import nimblix.in.HealthCareHub.response.UserStatusResponse;
import nimblix.in.HealthCareHub.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserStatusResponse updateUserStatus(UserStatusRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setActive(request.isActive()); // set active/inactive
        userRepository.save(user);

        String msg = request.isActive() ? "User activated successfully" : "User deactivated successfully";

        return UserStatusResponse.builder()
                .userId(user.getId())
                .active(user.isActive())
                .message(msg)
                .build();
    }
}