package nimblix.in.HealthCareHub.response;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicineResponse {

    private Long id;
    private String medicineName;
    private String manufacturer;
    private String description;
    private String dosage;
    private Double price;
    private Integer stockQuantity;
    private String isActive;
}

