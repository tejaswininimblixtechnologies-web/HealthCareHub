package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.request.UserStatusRequest;
import nimblix.in.HealthCareHub.response.UserStatusResponse;
import nimblix.in.HealthCareHub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserStatusResponse updateUserStatus(UserStatusRequest request) {
        UserStatusResponse response = new UserStatusResponse();

        // Fetch user by ID
        Optional<User> optionalUser = userRepository.findById(request.getUserId());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(request.isActive());  // Update active/inactive flag
            userRepository.save(user);           // Save changes

            response.setUserId(user.getId());
            response.setActive(user.isActive());
            response.setMessage("User status updated successfully.");
        } else {
            // User not found case
            response.setUserId(request.getUserId());
            response.setActive(false);
            response.setMessage("User not found.");
        }

        return response;
    }
}