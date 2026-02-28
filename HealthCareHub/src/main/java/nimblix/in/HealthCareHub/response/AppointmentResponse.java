package nimblix.in.HealthCareHub.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AppointmentResponse {

    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDateTime;
    private String status;
}