package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HospitalSearchResponse {

    private Long id;
    private String hospitalName;
    private String city;
    private String state;
  //  private String phoneNumber;

}
