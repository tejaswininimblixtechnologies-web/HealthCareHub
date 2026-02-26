package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Permission;
import nimblix.in.HealthCareHub.model.RolePermission;
import nimblix.in.HealthCareHub.repository.PermissionRepository;
import nimblix.in.HealthCareHub.repository.RolePermissionRepository;
import nimblix.in.HealthCareHub.request.RolePermissionRequest;
import nimblix.in.HealthCareHub.service.RoleService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;

    @Override
    public void assignPermissions(RolePermissionRequest request) {

        request.getPermissions().forEach(permissionName -> {

            Permission permission = permissionRepository.findByName(permissionName)
                    .orElseThrow(() -> new RuntimeException("Permission not found: " + permissionName));

            RolePermission mapping = new RolePermission();
            mapping.setRole(request.getRole());
            mapping.setPermission(permission);

            rolePermissionRepository.save(mapping);
        });
    }
}