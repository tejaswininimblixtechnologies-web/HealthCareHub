package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HospitalResponse {

    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String phone;
    private String email;
    private Integer totalBeds;
}
