package nimblix.in.HealthCareHub.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineAddRequest {

    private String medicineName;
    private String manufacturer;
    private Double price;
    private Integer stock;
    private String expiryDate;
}
