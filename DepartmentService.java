package nimblix.in.HealthCareHub.service;

import jakarta.validation.Valid;
import nimblix.in.HealthCareHub.model.Department;
import org.springframework.http.ResponseEntity;

public interface DepartmentService {

    ResponseEntity<?> updateDepartment(Long departmentId, @Valid Department request);

}