package nimblix.in.HealthCareHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import nimblix.in.HealthCareHub.dto.RoleRequest;
import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleRequest request) {
        Role role = roleService.createRole(request);
        return ResponseEntity.ok(role);
    }
}
