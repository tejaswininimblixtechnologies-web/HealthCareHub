package nimblix.in.HealthCareHub.response;

import lombok.Data;
import nimblix.in.HealthCareHub.model.Prescription;
import org.springframework.http.HttpStatus;

@Data
public class PrescriptionResponse<T> {
    private Integer status;
    private String message;
    private Prescription prescription;
}
