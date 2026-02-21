package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.utility.BeanUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Page<User> findByEmailContainingIgnoreCase(String email, Pageable pageable);

    Page<User> findByRole(Role role, Pageable pageable);

    Page<User> findByEnabled(boolean enabled, Pageable pageable);

    Page<User> findByEmailContainingIgnoreCaseAndRoleAndEnabled(
            String email,
            Role role,
            boolean enabled,
            Pageable pageable
    );

    static Optional<User> findByEmailStatic(String email) {
        return BeanUtil.getBean(UserRepository.class)
                .findByEmail(email);
    }
}
