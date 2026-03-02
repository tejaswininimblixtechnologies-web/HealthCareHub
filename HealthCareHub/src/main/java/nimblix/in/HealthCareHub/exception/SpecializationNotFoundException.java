package nimblix.in.HealthCareHub.exception;

import org.springframework.http.HttpStatus;

public class SpecializationNotFoundException extends BaseException {
    public SpecializationNotFoundException(String name) {
        super(
                "Specialization not found with name: " + name,
                HttpStatus.NOT_FOUND,
                ErrorCode.SPECIALIZATION_NOT_FOUND
                );
    }
}
