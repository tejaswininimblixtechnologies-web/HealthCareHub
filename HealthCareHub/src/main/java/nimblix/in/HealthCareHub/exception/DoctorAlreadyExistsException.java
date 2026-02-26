package nimblix.in.HealthCareHub.exception;

import org.springframework.http.HttpStatus;

public class DoctorAlreadyExistsException extends  BaseException{
    public DoctorAlreadyExistsException(String email) {
        super("Doctor already exists with email: " + email,
                HttpStatus.BAD_REQUEST,
                ErrorCode.DOCTOR_ALREADY_EXISTS);
    }
}
