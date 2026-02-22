package nimblix.in.HealthCareHub.dto;

import lombok.Data;

@Data
public class DoctorRegistrationRequest {
    private String name;                // use SAME field as Doctor entity
    private String email;
    private String password;
    private String phone;
    private Integer experienceYears;    // match your Doctor entity
    private String qualification;
}
