package nimblix.in.HealthCareHub.request;

import lombok.Data;

@Data
public class UserFilterRequest {

    private String email;
    private String role;
    private Boolean active;
}