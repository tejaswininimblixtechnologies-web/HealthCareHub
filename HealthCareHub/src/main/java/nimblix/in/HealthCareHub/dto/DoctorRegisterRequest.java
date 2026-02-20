package nimblix.in.HealthCareHub.dto;

import lombok.Data;

@Data
public class DoctorRegisterRequest {

    private String name;
    private Integer experienceYears;
    private String phoneNo;
    private String qualification;
    private Double consultationFee;
    private Long hospitalId;
    private Long specializationId;
}
