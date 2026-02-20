package nimblix.in.HealthCareHub.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorAvailabilityRequest {

    private Long doctorId;

    private String availableDate;
    private String startTime;
    private String endTime;

    private Boolean isAvailable;
}