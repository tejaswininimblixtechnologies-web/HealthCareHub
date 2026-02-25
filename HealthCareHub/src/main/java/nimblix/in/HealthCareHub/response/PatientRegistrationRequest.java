package nimblix.in.HealthCareHub.response;


import nimblix.in.HealthCareHub.response.PatientRegistrationRequest;
import lombok.Data;

@Data
public class PatientRegistrationRequest {

    private String name;
    private String email;
    private String phone;
    private String password;
    private String address;
}
