package nimblix.in.HealthCareHub.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DoctorResponse {

    private Long doctorId;
    private String name;
    private String specialization;
    private String email;
    private Long hospitalId;
}