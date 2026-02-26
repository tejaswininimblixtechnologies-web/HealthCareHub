package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecializationRepository
        extends JpaRepository<Specialization, Long> {

    // Find specialization using name
    Optional<Specialization> findByName(String name);
}