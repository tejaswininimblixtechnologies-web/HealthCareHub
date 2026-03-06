package nimblix.in.HealthCareHub.request;

import lombok.Data;

@Data
public class PatientRegistrationRequest {

    private Long patientId;

    private String name;
    private Integer age;
    private String gender;
    private String phone;
    private String disease;
    private String email;
    private Long hospitalId;
}