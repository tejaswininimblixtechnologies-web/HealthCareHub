package nimblix.in.HealthCareHub.request;

import lombok.Data;

@Data
public class PatientRequest {

    private String name;
    private Integer age;
    private String gender;
    private String phone;
    private String disease;
    private Long hospitalId;
}