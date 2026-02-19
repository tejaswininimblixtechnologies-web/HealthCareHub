package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.response.PaginatedUserResponse;
//import nimblix.in.HealthCareHub.model.User;

public interface UserService {

//    User saveUser(User user);

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
