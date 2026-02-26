package nimblix.in.HealthCareHub.request;

import lombok.Getter;
import lombok.Setter;
import nimblix.in.HealthCareHub.model.Specialization;

@Getter
@Setter
public class DoctorRegistrationRequest {

    private String name;
    private String email;
    private String password;
    private String phone;
    private Long hospitalId;
    private Long specializationId;   // ✅ Correct name & type
}