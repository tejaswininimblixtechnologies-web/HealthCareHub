package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // For login API
    Optional<User> findByEmail(String email);

    // For Spring Security authentication (username = email)
    Optional<User> findByEmailIgnoreCase(String email);

}
