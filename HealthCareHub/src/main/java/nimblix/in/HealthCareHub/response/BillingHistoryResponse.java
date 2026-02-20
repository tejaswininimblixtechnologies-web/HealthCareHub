package nimblix.in.HealthCareHub.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingHistoryResponse {

    private Long paymentId;
    private Double amount;
    private String paymentStatus;
    private LocalDateTime paymentDate;
    private String createdTime;
    private String updatedTime;

    // Appointment Details
    private Long appointmentId;
    private LocalDateTime appointmentDateTime;
    private String appointmentStatus;

    // Doctor Details
    private Long doctorId;
    private String doctorName;
    private String doctorSpecialization;

    // Patient Details
    private Long patientId;
    private String patientName;
}

