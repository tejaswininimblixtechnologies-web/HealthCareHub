package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {

    private Long id;
    private String name;
    private Integer experienceYears;
    private String phone;
    private String emailId;
    private String qualification;
    private String hospitalName;
    private String specializationName;
}
