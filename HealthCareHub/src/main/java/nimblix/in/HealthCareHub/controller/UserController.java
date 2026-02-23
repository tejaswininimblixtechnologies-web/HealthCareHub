package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PutMapping("/{id}/role")
    public User assignRole(@PathVariable Long id, @RequestParam String role) {
        return userService.assignRole(id, role);
    }
}