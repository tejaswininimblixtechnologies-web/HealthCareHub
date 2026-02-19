package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.User;

public interface UserService {
    User registerUser(User user);
    User assignRole(Long userId, String role);
}