package nimblix.in.HealthCareHub.request;

import lombok.Data;

@Data
public class PurchaseOrderRequest {

    private String medicineName;
    private Integer quantity;
    private Long vendorId;
    private Long hospitalId;
}