package nimblix.in.HealthCareHub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nimblix.in.HealthCareHub.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Optional<Hospital> findByNameIgnoreCase(String name);

    @Query("SELECT new nimblix.in.HealthCareHub.dto.HospitalDropdownDTO(h.id, h.name) FROM Hospital h")
    java.util.List<nimblix.in.HealthCareHub.dto.HospitalDropdownDTO> getHospitalDropdown();
}