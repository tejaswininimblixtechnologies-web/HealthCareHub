package nimblix.in.HealthCareHub.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientResponse {

    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private String phone;
    private String disease;

}
