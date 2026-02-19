package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.response.PaginatedUserResponse;
import nimblix.in.HealthCareHub.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PostMapping
//    public User saveUser(@RequestBody User user) {
//        return userService.saveUser(user);
//    }

    @GetMapping
    public PaginatedUserResponse getAllUsers(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,

            @RequestParam(required = false) String email,
            @RequestParam(required = false) Role role,
            @RequestParam(required = false) Boolean enabled
    ) {

        return userService.getAllUsers(
                page,
                size,
                sortBy,
                sortDir,
                email,
                role,
                enabled
        );
    }
}



//Basic Pagination
//GET http://localhost:9092/api/users?page=0&size=5


//Sorting
//GET http://localhost:9092/api/users?page=0&size=5&sortBy=email&sortDir=desc


//Filter by Email
//GET http://localhost:9092/api/users?email=gmail


//Filter by Role
//GET http://localhost:9092/api/users?role=ADMIN


//Filter by Enabled
//GET http://localhost:9092/api/users?enabled=true


//Combined Filtering
//GET http://localhost:9092/api/users?email=gmail&role=ADMIN&enabled=true
