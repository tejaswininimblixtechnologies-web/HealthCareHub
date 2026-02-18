package nimblix.in.HealthCareHub.dto;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class DoctorRegisterRequest {

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[A-Za-z .-]{3,50}+$",
            message = "Name must contain only letters")
    private String name;

    @NotNull(message = "Experience is required")
    @Min(value = 0, message = "Experience cannot be negative")
    @Max(value = 60, message = "Experience too large")
    private Integer experienceYears;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$",
            message = "Phone number must be exactly 10 digits")
    private String phoneNo;

    @NotBlank(message = "Qualification is required")
    @Pattern(regexp = "^[A-Za-z. ]+$",
            message = "Qualification must contain only letters")
    private String qualification;

    @NotNull(message = "Consultation fee is required")
    @Positive(message = "Consultation fee must be greater than 0")
    private Double consultationFee;

    @NotNull(message = "Hospital is required")
    private Long hospitalId;

    @NotNull(message = "Specialization is required")
    private Long specializationId;
}
