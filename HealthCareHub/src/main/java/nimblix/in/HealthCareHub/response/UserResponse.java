package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String email;

    // we return role as String (clean API response)
    private String role;

    private Boolean enabled;

    private String createdTime;

    private String updatedTime;
}