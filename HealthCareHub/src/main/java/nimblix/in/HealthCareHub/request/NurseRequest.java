package nimblix.in.HealthCareHub.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NurseRequest {

    private String name;
    private String phone;
    private String email;
    private Integer experienceYears;
    private String department;
    private Long hospitalId;
}
