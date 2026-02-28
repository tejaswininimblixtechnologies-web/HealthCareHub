package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.request.UserFilterRequest;
import nimblix.in.HealthCareHub.response.UserResponse;
import nimblix.in.HealthCareHub.service.UserService;

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

        // Edge Case Handling
        if (page < 0) page = 0;
        if (size <= 0) size = 10;

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        // STEP 1: Fetch paginated users from DB
        Page<User> userPage = userRepository.findAll(pageable);

        // STEP 2: Apply Filtering using doFilter()
        List<User> filteredUsers = userPage
                .getContent()
                .stream()
                .filter(user -> doFilter(user, request))
                .collect(Collectors.toList());

        // STEP 3: Convert Entity → Response DTO
        List<UserResponse> responseList = filteredUsers
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        // STEP 4: Return Page Object
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


      // Entity → DTO mapping
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