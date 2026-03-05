package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatientSearchResponse {

    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private String phone;
    private String disease;

}