package nimblix.in.HealthCareHub.model;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String name;
    private String email;
    private String phone;
    private String password;
    private Role role;
}
