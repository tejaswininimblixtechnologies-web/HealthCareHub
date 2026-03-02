package nimblix.in.HealthCareHub.exception;

public class UserNotFoundException extends ResourceNotFoundException {

    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }

    public UserNotFoundException(String email) {
        super("User not found with email: " + email);
    }
}
