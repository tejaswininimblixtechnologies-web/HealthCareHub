package nimblix.in.HealthCareHub.repository.specification;

import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.model.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> filterUsers(
            String email,
            Role role,
            Boolean enabled) {

        return (root, query, cb) -> {

            var predicates = cb.conjunction();

            if (email != null)
                predicates.getExpressions().add(
                        cb.like(cb.lower(root.get("email")),
                                "%" + email.toLowerCase() + "%"));

            if (role != null)
                predicates.getExpressions().add(
                        cb.equal(root.get("role"), role));

            if (enabled != null)
                predicates.getExpressions().add(
                        cb.equal(root.get("enabled"), enabled));

            return predicates;
        };
    }
}