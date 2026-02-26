package nimblix.in.HealthCareHub.exception;

public class AdminNotFoundException extends RuntimeException{

    public AdminNotFoundException(Long id) {
        super("Admin not found with id: " + id);
    }

    public AdminNotFoundException(String email) {
        super("Admin not found with email: " + email);
    }

}
