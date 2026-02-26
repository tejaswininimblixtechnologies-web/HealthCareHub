package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.UserFilterRequest;
import nimblix.in.HealthCareHub.response.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<UserResponse> getUsers(
            int page,
            int size,
            String sortBy,
            String direction,
            UserFilterRequest request);
}