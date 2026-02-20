package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.CreateUserRequest;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }
}
