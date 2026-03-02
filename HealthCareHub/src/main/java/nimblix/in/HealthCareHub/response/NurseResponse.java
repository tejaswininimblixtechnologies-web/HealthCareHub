package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NurseResponse {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private Integer experienceYears;
    private String department;
    private String hospitalName;
}
