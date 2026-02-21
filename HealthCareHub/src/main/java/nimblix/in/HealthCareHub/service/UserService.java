package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.response.PaginatedUserResponse;

public interface UserService {


    PaginatedUserResponse getAllUsers(
            int page,
            int size,
            String sortBy,
            String sortDir,
            String email,
            Role role,
            Boolean enabled
    );
}
