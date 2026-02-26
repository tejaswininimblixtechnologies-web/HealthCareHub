package nimblix.in.HealthCareHub.exception;

import org.springframework.http.HttpStatus;

public class HospitalValidationException extends BaseException {
    public HospitalValidationException(String message) {
        super(message,
                HttpStatus.BAD_REQUEST,
                ErrorCode.HOSPITAL_VALIDATION_FAILED);
    }
}
