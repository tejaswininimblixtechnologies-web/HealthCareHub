package nimblix.in.HealthCareHub.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSearchRequest {
    private String name;
    private String specialization;
    private Long hospitalId;
}
