package nimblix.in.HealthCareHub.request;

import nimblix.in.HealthCareHub.model.Role;
import java.util.Set;

public class RolePermissionRequest {

    private Role role;
    private Set<String> permissions;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}