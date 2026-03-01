package nimblix.in.HealthCareHub.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BillingResponse {

    private Long id;
    private Long patientId;
    private Double amount;
    private String paymentMode;
    private String status;
    private LocalDateTime createdAt;
}