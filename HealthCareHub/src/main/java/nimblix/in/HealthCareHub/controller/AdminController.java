package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.request.AssignPermissionRequest;
import nimblix.in.HealthCareHub.response.PermissionResponse;
import nimblix.in.HealthCareHub.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/assign-permissions")
    public ResponseEntity<String> assignPermissions(@RequestBody AssignPermissionRequest request) {
        return ResponseEntity.ok(adminService.assignPermissions(request));
    }

    @GetMapping("/permissions/{role}")
    public ResponseEntity<PermissionResponse> getPermissions(@PathVariable Role role) {
        return ResponseEntity.ok(adminService.getPermissions(role));
    }
}
