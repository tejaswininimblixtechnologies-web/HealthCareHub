package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Permission;
import nimblix.in.HealthCareHub.repository.PermissionRepository;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.request.RolePermissionRequest;
import nimblix.in.HealthCareHub.service.HospitalService;
import nimblix.in.HealthCareHub.service.RoleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;
    private final PermissionRepository permissionRepository;
    private final RoleService roleService;

    @PostMapping("/register")
    public String registerHospital(@RequestBody HospitalRegistrationRequest request) {
        return hospitalService.registerHospital(request);
    }

    // ⭐ CREATE PERMISSION
    @PostMapping("/permissions")
    public Permission createPermission(@RequestParam String name) {
        return permissionRepository.save(new Permission(name));
    }

    // ⭐ ASSIGN ROLE PERMISSIONS
    @PostMapping("/assign-permissions")
    public String assignPermissions(@RequestBody RolePermissionRequest request) {
        roleService.assignPermissions(request);
        return "Permissions Assigned Successfully";
    }
}