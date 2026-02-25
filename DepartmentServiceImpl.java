package nimblix.in.HealthCareHub.serviceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Department;
import nimblix.in.HealthCareHub.repository.DepartmentRepository;
import nimblix.in.HealthCareHub.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public ResponseEntity<?> updateDepartment(Long departmentId, @Valid  Department request) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // update fields
        department.setName(request.getName());
        department.setDescription(request.getDescription());

        departmentRepository.save(department);

        return ResponseEntity.ok("Department updated successfully");
    }
}