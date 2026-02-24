package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponse {
    private Long id;
    private String name;
    private Integer experienceYears;
    private String phone;
    private String emailId;
    private String qualification;
    private String hospitalName;
    private Long hospitalId;
    private String specializationName;
    private Long specializationId;
}
