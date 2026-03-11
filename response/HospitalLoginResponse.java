package nimblix.in.HealthCareHub.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HospitalLoginResponse {

    private String token;
    private String message;
}