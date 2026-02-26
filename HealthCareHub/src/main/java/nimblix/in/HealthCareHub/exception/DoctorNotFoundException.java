package nimblix.in.HealthCareHub.exception;

import org.springframework.http.HttpStatus;

public class DoctorNotFoundException extends BaseException{
    public DoctorNotFoundException(Long id) {
        super("Doctor not found with id: " + id,
                HttpStatus.NOT_FOUND,
                ErrorCode.DOCTOR_NOT_FOUND);
    }
}
