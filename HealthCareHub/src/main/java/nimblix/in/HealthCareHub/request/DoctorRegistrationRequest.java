package nimblix.in.HealthCareHub.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorRegistrationRequest {

    private String doctorName;
    private String doctorEmail;
    private String password;
    private String qualification;
    private String experience;
    private String description;
    private String phoneNo;
    private String doctorId;
    private String hospitalName;
    private String specializationName;
}