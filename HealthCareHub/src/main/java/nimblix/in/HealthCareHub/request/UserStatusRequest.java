package nimblix.in.HealthCareHub.request;



public class UserStatusRequest {
    private Long userId;
    private boolean active;

    // Getters & Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
