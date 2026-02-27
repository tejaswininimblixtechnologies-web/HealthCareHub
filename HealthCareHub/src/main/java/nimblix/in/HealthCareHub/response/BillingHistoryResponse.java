package nimblix.in.HealthCareHub.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingHistoryResponse {

    private Long paymentId;
    private Double amount;
    private String paymentStatus;
    private String paymentDate;
    private String createdTime;
    private String updatedTime;

    // Appointment Details
    private Long appointmentId;
    private String appointmentDateTime;
    private String appointmentStatus;

    // Doctor Details
    private Long doctorId;
    private String doctorName;
    private String doctorSpecialization;

    // Patient Details
    private Long patientId;
    private String patientName;
}
