package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.repository.specification.UserSpecification;
import nimblix.in.HealthCareHub.request.UserFilterRequest;
import nimblix.in.HealthCareHub.response.UserResponse;
import nimblix.in.HealthCareHub.service.UserService;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<UserResponse> getUsers(
            int page,
            int size,
            String sortBy,
            String direction,
            UserFilterRequest request) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Role roleEnum = null;
        if (request.getRole() != null) {
            roleEnum = Role.valueOf(request.getRole().toUpperCase());
        }

        Specification<User> spec =
                UserSpecification.filterUsers(
                        request.getEmail(),
                        roleEnum,
                        request.getEnabled());

        Page<User> users = userRepository.findAll(spec, pageable);

        return users.map(this::convertToDTO);
    }

    private UserResponse convertToDTO(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .enabled(user.isEnabled())
                .createdTime(user.getCreatedTime())
                .build();
    }
}