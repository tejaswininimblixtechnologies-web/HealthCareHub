package nimblix.in.HealthCareHub.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDto {

    private Long id;

    private String name;
    private Integer age;
    private String gender;
    private String phone;
    private String disease;
}