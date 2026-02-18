package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.CreateUserRequest;
import nimblix.in.HealthCareHub.model.User;

public interface UserService {

    User createUser(CreateUserRequest request);
}
