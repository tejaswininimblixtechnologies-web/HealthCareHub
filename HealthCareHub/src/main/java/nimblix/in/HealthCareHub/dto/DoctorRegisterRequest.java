package nimblix.in.HealthCareHub.dto;

import lombok.Data;

@Data
public class DoctorRegisterRequest {

    private String name;
    private Integer experienceYears;
    @Pattern(regexp = "^[0-9]{10}$",
    private String phoneNo;
    private String qualification;
    private Double consultationFee;
    private Long hospitalId;
    private Long specializationId;
}
