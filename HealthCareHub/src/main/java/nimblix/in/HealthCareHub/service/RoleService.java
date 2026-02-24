package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.dto.RolePermissionDTO;

public interface RoleService {
    void assignPermissions(RolePermissionDTO dto);
}