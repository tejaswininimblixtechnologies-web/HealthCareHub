package nimblix.in.HealthCareHub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Department;
import nimblix.in.HealthCareHub.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PutMapping("/update/{departmentId}")
    public ResponseEntity<?> updateDepartment(
            @PathVariable Long departmentId,
            @Valid @RequestBody Department request) {

        return ResponseEntity.ok(
                departmentService.updateDepartment(departmentId, request)
        );
    }
}