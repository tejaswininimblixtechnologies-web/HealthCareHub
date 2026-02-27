package nimblix.in.HealthCareHub.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponseDTO {
    private Long id;
    private String name;
    private String emailId;
    private Integer experienceYears;
    private String phone;
    private String qualification;
    private String experience;
    private String description;
    private String doctorId;
    private String specializationName;
    private String hospitalName;
}
