package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByName(String name);
}
