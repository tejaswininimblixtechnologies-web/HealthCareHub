package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.UserStatusRequest;
import nimblix.in.HealthCareHub.response.UserStatusResponse;

public interface UserService {

    UserStatusResponse updateUserStatus(UserStatusRequest request);
}