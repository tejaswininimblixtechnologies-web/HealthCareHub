package nimblix.in.HealthCareHub.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppointmentResponse {

    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private String appointmentDateTime;
    private String status;

}