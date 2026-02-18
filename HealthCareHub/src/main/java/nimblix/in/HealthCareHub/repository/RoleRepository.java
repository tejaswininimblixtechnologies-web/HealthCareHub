package nimblix.in.HealthCareHub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.model.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByName(RoleName name);

    Optional<Role> findByName(RoleName name);
}

