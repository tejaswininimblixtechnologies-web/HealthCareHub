package nimblix.in.HealthCareHub.service;

import org.springframework.data.domain.Page;
import nimblix.in.HealthCareHub.response.UserResponse;
import nimblix.in.HealthCareHub.request.UserFilterRequest;

public interface UserService {

    Page<UserResponse> getUsers(
            int page,
            int size,
            String sortBy,
            String direction,
            UserFilterRequest request
    );
}