package nimblix.in.HealthCareHub.exception;

public class NurseNotFoundException extends RuntimeException {
    public NurseNotFoundException(Long id) {
        super("Nurse not found with id: " + id);
    }

    public NurseNotFoundException(String name) {
        super("Nurse not found with name: " + name);
    }
}
