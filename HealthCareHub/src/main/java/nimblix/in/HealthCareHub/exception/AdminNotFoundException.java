package nimblix.in.HealthCareHub.exception;

public class AdminNotFoundException extends ResourceNotFoundException{

    public AdminNotFoundException(Long id) {
        super("Admin not found with id: " + id);
    }

    public AdminNotFoundException(String email) {
        super("Admin not found with email: " + email);
    }

}
