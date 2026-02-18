package nimblix.in.HealthCareHub.dto;

import lombok.Data;

@Data
public class PurchaseOrderRequestDto {

    private String medicineName;
    private Integer quantity;
    private Long vendorId;
}