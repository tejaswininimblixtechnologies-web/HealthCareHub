package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.request.UserStatusRequest;
import nimblix.in.HealthCareHub.response.UserStatusResponse;
import nimblix.in.HealthCareHub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController                // <--- must be @RestController
@RequestMapping("/api/users")  // <--- base URL
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/status")    // <--- maps POST /api/users/status
    public ResponseEntity<UserStatusResponse> updateUserStatus(@RequestBody UserStatusRequest request) {
        UserStatusResponse response = userService.updateUserStatus(request);
        return ResponseEntity.ok(response);
    }
}