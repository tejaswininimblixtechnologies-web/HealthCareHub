package nimblix.in.HealthCareHub.exception;

import org.springframework.http.HttpStatus;

public class HospitalNotFoundException extends BaseException {
    public HospitalNotFoundException(Long id) {
        super("Hospital not found with id: " + id,
                HttpStatus.NOT_FOUND,
                ErrorCode.HOSPITAL_NOT_FOUND);
    }
}
