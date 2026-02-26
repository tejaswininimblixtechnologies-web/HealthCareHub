package nimblix.in.HealthCareHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nimblix.in.HealthCareHub.request.UserStatusRequest;
import nimblix.in.HealthCareHub.response.UserStatusResponse;
import nimblix.in.HealthCareHub.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/status")
    public ResponseEntity<UserStatusResponse> updateUserStatus(@RequestBody UserStatusRequest request) {
        UserStatusResponse response = userService.updateUserStatus(request);
        return ResponseEntity.ok(response);
    }
}