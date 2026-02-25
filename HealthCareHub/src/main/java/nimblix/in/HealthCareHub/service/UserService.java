package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.UserStatusRequest;
import nimblix.in.HealthCareHub.response.UserStatusResponse;

public interface UserService {

    /**
     * Update the active/inactive status of a user
     * @param request UserStatusRequest containing userId and active flag
     * @return UserStatusResponse with updated status and message
     */
    UserStatusResponse updateUserStatus(UserStatusRequest request);
}