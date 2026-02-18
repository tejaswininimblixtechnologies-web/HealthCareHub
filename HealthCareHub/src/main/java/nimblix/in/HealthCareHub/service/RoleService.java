package nimblix.in.HealthCareHub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nimblix.in.HealthCareHub.dto.RoleRequest;
import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.model.RoleName;
import nimblix.in.HealthCareHub.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(RoleRequest request) {

        // 🔹 Convert String → ENUM
        RoleName roleName;
        try {
            roleName = RoleName.valueOf(request.getName().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role name");
        }

        // 🔹 Duplicate check
        if (roleRepository.existsByName(roleName)) {
            throw new RuntimeException("Role already exists");
        }

        // 🔹 Save role
        Role role = new Role();
        role.setName(roleName);

        return roleRepository.save(role);
    }
}
