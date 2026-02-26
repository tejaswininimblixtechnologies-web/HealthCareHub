package nimblix.in.HealthCareHub.exception;

import org.springframework.http.HttpStatus;

public class HospitalAlreadyExistsException extends BaseException {
    public HospitalAlreadyExistsException(String name) {
        super("Hospital already exists with name: " + name,
                HttpStatus.BAD_REQUEST,
                ErrorCode.HOSPITAL_ALREADY_EXISTS);
    }
}
