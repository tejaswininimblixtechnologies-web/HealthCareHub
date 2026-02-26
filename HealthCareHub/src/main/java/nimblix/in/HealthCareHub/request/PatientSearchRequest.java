package nimblix.in.HealthCareHub.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientSearchRequest {
    private String name;
    private String bloodGroup;
    private String phone;
}