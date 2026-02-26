package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.RolePermissionRequest;

public interface RoleService {
    void assignPermissions(RolePermissionRequest request);
}