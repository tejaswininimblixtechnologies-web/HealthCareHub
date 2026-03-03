package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MedicineResponse {

        private Long id;
        private String medicineName;
        private String manufacturer;
        private Double price;
        private Integer stockQuantity;
        private Long hospitalId;
    }

