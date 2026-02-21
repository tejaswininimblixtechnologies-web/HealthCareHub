package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.response.PaginatedUserResponse;
import nimblix.in.HealthCareHub.service.UserService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public PaginatedUserResponse getAllUsers(
            int page,
            int size,
            String sortBy,
            String sortDir,
            String email,
            Role role,
            Boolean enabled
    ) {

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<User> userPage;

        // Filtering logic
        if (email != null && role != null && enabled != null) {
            userPage = userRepository
                    .findByEmailContainingIgnoreCaseAndRoleAndEnabled(
                            email, role, enabled, pageable);

        } else if (email != null) {
            userPage = userRepository
                    .findByEmailContainingIgnoreCase(email, pageable);

        } else if (role != null) {
            userPage = userRepository
                    .findByRole(role, pageable);

        } else if (enabled != null) {
            userPage = userRepository
                    .findByEnabled(enabled, pageable);

        } else {
            userPage = userRepository.findAll(pageable);
        }
        return PaginatedUserResponse.builder()
                .users(userPage.getContent())
                .currentPage(userPage.getNumber())
                .totalPages(userPage.getTotalPages())
                .totalElements(userPage.getTotalElements())
                .pageSize(userPage.getSize())
                .build();
    }
}
