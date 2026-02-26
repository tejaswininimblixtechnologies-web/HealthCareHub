package nimblix.in.HealthCareHub.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStatusRequest {

    private Long userId;
    private boolean active;  // true = activate, false = deactivate
}