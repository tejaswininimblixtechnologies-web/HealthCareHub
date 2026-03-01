package nimblix.in.HealthCareHub.request;

import lombok.Data;

@Data
public class BillingRequest {

    private Long patientId;
    private Double amount;
    private String paymentMode;
    private String status;
}