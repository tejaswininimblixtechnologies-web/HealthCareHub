package nimblix.in.HealthCareHub.request;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    private String name;
    private String email;
    private String phone;
    private String password;
    private Set<String> roles;
}