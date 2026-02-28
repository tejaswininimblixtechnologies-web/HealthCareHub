package nimblix.in.HealthCareHub.response;

import nimblix.in.HealthCareHub.model.Role;
import java.util.Set;

public class PermissionResponse {

    private String message;
    private Role role;
    private Set<String> permissions;

    public PermissionResponse(String message, Role role, Set<String> permissions) {
        this.message = message;
        this.role = role;
        this.permissions = permissions;
    }

    public String getMessage() { return message; }
    public Role getRole() { return role; }
    public Set<String> getPermissions() { return permissions; }
}