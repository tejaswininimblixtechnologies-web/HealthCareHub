package nimblix.in.HealthCareHub.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HospitalResponseDTO {

    private Long id;
    private String name;
}