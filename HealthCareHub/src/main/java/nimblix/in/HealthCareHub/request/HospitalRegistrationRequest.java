package nimblix.in.HealthCareHub.request;

import lombok.Getter;
import lombok.Setter;
import nimblix.in.HealthCareHub.model.Role;

import java.util.List;

@Getter
@Setter
public class HospitalRegistrationRequest {

    private String name;
    private String address;
    private String city;
    private String state;
    private String phone;
    private String email;
    private Integer totalBeds;


    private String password;
    private Role role;
}