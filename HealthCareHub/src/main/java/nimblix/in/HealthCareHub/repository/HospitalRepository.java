package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    @Query("""
        SELECT h FROM Hospital h
        WHERE (:name IS NULL OR LOWER(h.name) LIKE LOWER(CONCAT('%', :name, '%')))
        AND (:city IS NULL OR LOWER(h.city) = LOWER(:city))
        AND (:state IS NULL OR LOWER(h.state) = LOWER(:state))
        AND (:minBeds IS NULL OR h.totalBeds >= :minBeds)
    """)
    List<Hospital> searchAndFilter(
            @Param("name") String name,
            @Param("city") String city,
            @Param("state") String state,
            @Param("minBeds") Integer minBeds
    );
}