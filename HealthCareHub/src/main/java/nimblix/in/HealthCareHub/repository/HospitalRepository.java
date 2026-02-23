package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.response.LocationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {
    Optional<Hospital> findByName(String hospitalName);
    @Query("""
           SELECT DISTINCT new nimblix.in.HealthCareHub.response.LocationResponse(
                h.address,
                h.city,
                h.state
           )
           FROM Hospital h
           WHERE h.city IS NOT NULL
           """)
    List<LocationResponse> findAllDistinctLocations();
}
