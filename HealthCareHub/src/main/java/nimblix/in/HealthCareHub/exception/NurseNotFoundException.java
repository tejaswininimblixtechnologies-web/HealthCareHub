package nimblix.in.HealthCareHub.exception;

public class NurseNotFoundException extends RuntimeException{
    public NurseNotFoundException(String s) {
        super("Nurse Not Found");
    }
}
