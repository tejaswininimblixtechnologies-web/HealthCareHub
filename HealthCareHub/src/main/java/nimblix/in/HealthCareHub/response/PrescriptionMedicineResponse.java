package nimblix.in.HealthCareHub.response;

import lombok.Data;
import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.model.PrescriptionMedicines;
import org.springframework.http.HttpStatus;

@Data
public class PrescriptionMedicineResponse<PrescriptionMedicines> {
    private Integer status;
    private String message;
    private PrescriptionMedicines prescriptionMedicines;
}
