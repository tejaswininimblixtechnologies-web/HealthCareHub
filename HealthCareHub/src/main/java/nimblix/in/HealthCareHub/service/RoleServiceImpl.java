package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.dto.RolePermissionDTO;
import nimblix.in.HealthCareHub.model.Permission;
import nimblix.in.HealthCareHub.model.RolePermission;
import nimblix.in.HealthCareHub.repository.PermissionRepository;
import nimblix.in.HealthCareHub.repository.RolePermissionRepository;
import nimblix.in.HealthCareHub.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    public void assignPermissions(RolePermissionDTO dto) {

        dto.getPermissions().forEach(permissionName -> {

            Permission permission = permissionRepository.findByName(permissionName)
                    .orElseThrow(() -> new RuntimeException("Permission not found: " + permissionName));

            RolePermission mapping = new RolePermission();
            mapping.setRole(dto.getRole());
            mapping.setPermission(permission);

            rolePermissionRepository.save(mapping);
        });
    }
}