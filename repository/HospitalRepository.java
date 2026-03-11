package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Specialization;
import nimblix.in.HealthCareHub.response.DropdownResponse;
import nimblix.in.HealthCareHub.response.HospitalOverviewResponse;
import nimblix.in.HealthCareHub.response.HospitalResponse;
import nimblix.in.HealthCareHub.response.HospitalSpecializationResponse;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {
    Optional<Hospital> findByName(String name);



    @Query("""
    SELECT new nimblix.in.HealthCareHub.response.HospitalResponse(
    h.id,
    h.name,
    h.address,
    h.city,
    h.state,
    h.phone,
    h.email,
    h.totalBeds
)
FROM Hospital h
""")
    List<HospitalResponse> getAllHospitals();

    List<Hospital> findByNameContainingIgnoreCase(String name);
    /* ContainingIgnoreCase allows searching like
    Apollo, Apollo Hospital, Apollo Clinic */


    Optional<Hospital> findByEmail(String email);



    // Query to fetch total beds
    @Query("SELECT COALESCE(SUM(h.totalBeds),0) FROM Hospital h")
    Long getTotalBeds();

    // Query to fetch average hospital rating
    @Query("SELECT COALESCE(AVG(h.rating),0) FROM Hospital h")
    Double getAverageRating();

    @Query("""
       SELECT new nimblix.in.HealthCareHub.response.DropdownResponse(
       h.id,
       h.name
       )
       FROM Hospital h
       ORDER BY h.name
       """)
    List<DropdownResponse> getHospitalDropdown();


    /*Query used to fetch hospital overview details for dashboard table
 We join hospital and doctor tables to calculate doctor count */
    @Query("""
SELECT new nimblix.in.HealthCareHub.response.HospitalOverviewResponse(
h.id,
h.name,
CONCAT(h.city, ', ', h.state),
h.rating,
COUNT(d.id),
h.totalBeds,
CASE WHEN h.isActive = true THEN 'Active' ELSE 'Inactive' END
)
FROM Hospital h
LEFT JOIN Doctor d ON d.hospital.id = h.id
GROUP BY h.id, h.name, h.city, h.state, h.rating, h.totalBeds, h.isActive
ORDER BY h.name
""")
    List<   HospitalOverviewResponse> getHospitalOverview();



    // Query used to fetch hospitals based on specialization
    @Query("""
    SELECT new nimblix.in.HealthCareHub.response.HospitalSpecializationResponse(
    h.id,
    h.name,
    CONCAT(h.city, ', ', h.state),
    s.name
    )
    FROM Doctor d
    JOIN d.hospital h
    JOIN d.specialization s
    WHERE LOWER(s.name) = LOWER(:specialization)
    GROUP BY h.id, h.name, h.city, h.state, s.name
    ORDER BY h.name
    """)
    List<HospitalSpecializationResponse> findHospitalsBySpecialization(String specialization);

}
