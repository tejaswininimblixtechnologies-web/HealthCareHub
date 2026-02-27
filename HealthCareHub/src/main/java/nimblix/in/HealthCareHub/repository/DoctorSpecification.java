package nimblix.in.HealthCareHub.repository;

import jakarta.persistence.criteria.Predicate;
import nimblix.in.HealthCareHub.model.Doctor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DoctorSpecification {

    public static Specification<Doctor> withFilters(String name, Long specializationId, Long hospitalId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (specializationId != null) {
                predicates.add(criteriaBuilder.equal(root.get("specialization").get("id"), specializationId));
            }

            if (hospitalId != null) {
                predicates.add(criteriaBuilder.equal(root.get("hospital").get("id"), hospitalId));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
