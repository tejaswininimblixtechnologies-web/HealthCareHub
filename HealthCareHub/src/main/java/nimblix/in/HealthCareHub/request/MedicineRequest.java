package nimblix.in.HealthCareHub.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineRequest {

    private String medicineName;
    private String manufacturer;
    private String description;
    private String dosage;
    private Double price;
    private Integer stockQuantity;
}
