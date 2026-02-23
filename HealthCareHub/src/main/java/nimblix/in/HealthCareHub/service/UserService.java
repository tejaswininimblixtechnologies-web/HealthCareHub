package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.UserRequest;
import nimblix.in.HealthCareHub.response.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest request);
}