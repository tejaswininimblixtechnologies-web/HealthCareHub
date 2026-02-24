package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.dto.RolePermissionDTO;
import nimblix.in.HealthCareHub.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/assign-permissions")
    public String assignPermissions(@RequestBody RolePermissionDTO dto) {
        roleService.assignPermissions(dto);
        return "Permissions Assigned Successfully";
    }
}