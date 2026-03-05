package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nimblix.in.HealthCareHub.model.Role;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class PermissionResponse {

    private String message;
    private Role role;
    private Set<String> permissions;

}