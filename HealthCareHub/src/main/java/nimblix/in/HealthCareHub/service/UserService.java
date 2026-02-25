package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.UserRegistrationRequest;

public interface UserService {

    String registerUser(UserRegistrationRequest request);
}
