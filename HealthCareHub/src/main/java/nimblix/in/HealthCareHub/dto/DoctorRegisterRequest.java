package nimblix.in.HealthCareHub.dto;

import lombok.Data;

@Data
public class DoctorRegisterRequest {

    // Doctor Details
    private String name;
    private Integer experienceYears;
    private String phone;
    private String qualification;

    // Login Details
    private String email;
    private String password;

    // Relations
    private Long hospitalId;
    private Long specializationId;
}