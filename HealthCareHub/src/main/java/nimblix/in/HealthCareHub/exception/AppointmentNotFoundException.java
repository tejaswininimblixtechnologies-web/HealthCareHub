package nimblix.in.HealthCareHub.exception;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException(String message) {
        super(message);
    }
}