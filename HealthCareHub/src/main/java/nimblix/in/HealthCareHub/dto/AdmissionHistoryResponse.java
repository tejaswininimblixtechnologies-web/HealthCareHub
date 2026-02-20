package nimblix.in.HealthCareHub.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdmissionHistoryResponse {

    private Long admissionId;

    private String admissionDate;

    private String dischargeDate;

    private String status;

    private Long patientId;
    private String patientName;

    private Long doctorId;
    private String doctorName;

    private Long hospitalId;
    private String hospitalName;
}
