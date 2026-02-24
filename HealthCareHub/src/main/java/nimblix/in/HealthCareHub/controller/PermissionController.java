package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.Permission;
import nimblix.in.HealthCareHub.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionRepository permissionRepository;

    @PostMapping
    public Permission createPermission(@RequestParam String name) {
        return permissionRepository.save(new Permission(name));
    }
}