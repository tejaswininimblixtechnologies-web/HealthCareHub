package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.request.UserFilterRequest;
import nimblix.in.HealthCareHub.response.UserResponse;
import nimblix.in.HealthCareHub.service.UserService;
import nimblix.in.HealthCareHub.helper.PaginationHelper;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        Pageable pageable = PaginationHelper.createPageable(
                page, size, sortBy, direction
        );

        Page<User> userPage = userRepository.findAll(pageable);

        List<User> filteredUsers = userPage
                .getContent()
                .stream()
                .filter(user -> doFilter(user, request))
                .collect(Collectors.toList());

        List<UserResponse> responseList = filteredUsers
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(
                responseList,
                pageable,
                userPage.getTotalElements()
        );
    }

    private boolean doFilter(User user, UserFilterRequest request) {

        if (request == null) return true;

        // Email filter
        if (request.getEmail() != null &&
                !user.getEmail().toLowerCase()
                        .contains(request.getEmail().toLowerCase())) {
            return false;
        }

        // Role filter
        if (request.getRole() != null &&
                (user.getRole() == null ||
                        !user.getRole().name()
                                .equalsIgnoreCase(request.getRole()))) {
            return false;
        }

        // Enabled filter
        if (request.getActive() != null &&
                user.isEnabled() != request.getActive()) {
            return false;
        }

        return true;
    }

    private UserResponse mapToResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole() != null ? user.getRole().name() : null)
                .enabled(user.isEnabled())
                .createdTime(user.getCreatedTime())
                .updatedTime(user.getUpdatedTime())
                .build();
    }
}