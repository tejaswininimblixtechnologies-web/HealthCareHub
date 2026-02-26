package nimblix.in.HealthCareHub.response;

import lombok.Builder;
import lombok.Data;
import nimblix.in.HealthCareHub.model.Role;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String email;
    private Role role;
    private boolean enabled;
    private String createdTime;
}