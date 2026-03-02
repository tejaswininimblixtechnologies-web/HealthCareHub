package nimblix.in.HealthCareHub.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {

    private String name;
    private Integer age;
    private String gender;
    private String phone;
    private String disease;
    private Long hospitalId;
}
