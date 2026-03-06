package nimblix.in.HealthCareHub.request;

import lombok.Data;

@Data
public class AppointmentRequest {

    private Long patientId;
    private Long doctorId;
    private String appointmentDateTime;

}