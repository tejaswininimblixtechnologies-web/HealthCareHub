package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.request.AssignPermissionRequest;
import nimblix.in.HealthCareHub.response.PermissionResponse;

public interface AdminService {

    String assignPermissions(AssignPermissionRequest request);

    PermissionResponse getPermissions(Role role);
}