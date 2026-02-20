package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDTO {

    private Long appointmentId;

    // Patient info
    private Long patientId;
    private String patientName;
    private String patientPhone;

    // Doctor info
    private Long doctorId;
    private String doctorName;
    private String doctorSpecialization;

    // Appointment details
    private LocalDateTime appointmentDateTime;
    private String status;
}
