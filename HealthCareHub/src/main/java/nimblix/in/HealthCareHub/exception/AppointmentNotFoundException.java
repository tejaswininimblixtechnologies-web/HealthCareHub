package nimblix.in.HealthCareHub.exception;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(Long id) {
        super("Appointment not found with id: " + id);
    }

    public AppointmentNotFoundException(String reference) {
        super("Appointment not found with reference: " + reference);
    }
}
