package nimblix.in.HealthCareHub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nimblix.in.HealthCareHub.dto.RoleRequest;
import nimblix.in.HealthCareHub.exception.RoleAlreadyExistsException;
import nimblix.in.HealthCareHub.exception.InvalidRoleException;   // ⭐ ADD THIS
import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.model.RoleName;
import nimblix.in.HealthCareHub.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(RoleRequest request) {

        // ✅ NULL / EMPTY CHECK
        if (request.getName() == null || request.getName().isBlank()) {
            throw new InvalidRoleException("Invalid role name");
        }

        // ✅ Convert String → ENUM
        RoleName roleName;
        try {
            roleName = RoleName.valueOf(request.getName().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidRoleException("Invalid role name");
        }

        // ✅ Duplicate check
        if (roleRepository.existsByName(roleName)) {
            throw new RoleAlreadyExistsException("Role already exist");
        }

        // ✅ Save role
        Role role = new Role();
        role.setName(roleName);

        return roleRepository.save(role);
    }
}
