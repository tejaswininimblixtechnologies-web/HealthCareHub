package nimblix.in.HealthCareHub.request;

import lombok.Getter;
import lombok.Setter;
import nimblix.in.HealthCareHub.model.Role;

import java.util.Set;

@Getter
@Setter
public class AssignPermissionRequest {

    private Role role;
    private Set<String> permissions;

}