package nimblix.in.HealthCareHub.service;

import java.util.List;
import nimblix.in.HealthCareHub.entity.Department;

public interface DepartmentService {

    Department saveDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentById(Long id);
}
