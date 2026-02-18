package main.java.nimblix.in.HealthCareHub.exception;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(String message) {
        super("Appointment Not Found");
    }
}
