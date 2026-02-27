package nimblix.in.HealthCareHub.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorRegistrationRequest {

    @NotBlank(message = "Doctor name is required")
    private String doctorName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Doctor email is required")
    private String doctorEmail;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Qualification is required")
    private String qualification;

    @NotNull(message = "Experience is required")
    @Min(value = 0, message = "Experience cannot be negative")
    private Long experience;

    private String description;

    @NotBlank(message = "Phone number is required")
    private String phoneNo;

    @NotNull(message = "Hospital Id is required")
    private Long hospitalId;

    @NotBlank(message = "Specialization name is required")
    private String specializationName;

    private Long doctorId;
}