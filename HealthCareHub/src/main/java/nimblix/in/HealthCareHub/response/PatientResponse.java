package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {

    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private String phone;
    private String disease;
    private String hospitalName;
}
