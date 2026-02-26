package nimblix.in.HealthCareHub.exception;

import org.springframework.http.HttpStatus;

public class DoctorValidationException extends BaseException{
    public DoctorValidationException(String message) {
        super(message,
                HttpStatus.BAD_REQUEST,
                ErrorCode.DOCTOR_VALIDATION_FAILED);
    }
}
