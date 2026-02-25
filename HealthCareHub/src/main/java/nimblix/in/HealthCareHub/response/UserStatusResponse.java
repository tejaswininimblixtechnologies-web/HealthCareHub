package nimblix.in.HealthCareHub.response;


public class UserStatusResponse {
    private Long userId;
    private boolean active;
    private String message;

    // Getters & Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}