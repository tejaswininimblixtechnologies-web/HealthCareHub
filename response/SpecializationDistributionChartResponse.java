package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecializationDistributionChartResponse {

    private String status;
    private String message;
    private List<SpecializationDistributionResponse> data;

}