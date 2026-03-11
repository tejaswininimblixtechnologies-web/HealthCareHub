package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.response.DoctorProfileResponse;
import nimblix.in.HealthCareHub.response.SpecializationDistributionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Optional<Doctor> findByEmailId(String emailId);

    Optional<Doctor> findByIdAndHospitalId(Long doctorId, Long hospitalId);

    @Query("""
            SELECT new nimblix.in.HealthCareHub.response.DoctorProfileResponse(
                d.id,
                d.name,
                d.emailId,
                d.phone,
                d.qualification,
                d.experienceYears,
                s.id,
                s.name,
                h.id,
                h.name,
                h.address,
                h.city,
                h.state,
                h.phone,
                h.email,
                h.totalBeds
            )
            FROM Doctor d
            LEFT JOIN d.specialization s
            LEFT JOIN d.hospital h
            WHERE d.id = :doctorId
            """)
    Optional<DoctorProfileResponse> findDoctorProfileById(@Param("doctorId") Long doctorId);

    List<DoctorProfileResponse> findBySpecialization_NameIgnoreCase(String name);




    @Query("""
        SELECT COUNT(DISTINCT d.specialization)
        FROM Doctor d
        WHERE d.hospital.id = :hospitalId
        """)
    Long countSpecializationsByHospitalId(@Param("hospitalId") Long hospitalId);



    @Query("""
        SELECT COUNT(d)
        FROM Doctor d
        WHERE d.hospital.id = :hospitalId
        """)
    Long countDoctorsByHospitalId(@Param("hospitalId") Long hospitalId);

    @Query("SELECT d FROM Doctor d WHERE d.hospital.id = :hospitalId")
    List<Doctor> findDoctorsByHospitalId(Long hospitalId);

    // Query to fetch active doctors
    @Query("SELECT COUNT(d) FROM Doctor d WHERE d.isActive='Y'")
    Long countActiveDoctors();




    /* Query used to fetch specialization distribution for dashboard pie chart
     It counts number of doctors in each specialization */
    @Query("""
    SELECT new nimblix.in.HealthCareHub.response.SpecializationDistributionResponse(
    s.name,
    COUNT(d.id)
    )
    FROM Doctor d
    JOIN d.specialization s
    GROUP BY s.name
    ORDER BY COUNT(d.id) DESC
    """)
    List<SpecializationDistributionResponse> getSpecializationDistribution();


    Optional<Doctor> findByPhone(String phone);



    // Query used to map doctor entity to response DTO
    @Query("""
SELECT new nimblix.in.HealthCareHub.response.DoctorProfileResponse(
d.id,
d.name,
d.emailId,
d.phone,
d.qualification,
d.experienceYears,
s.id,
s.name,
h.id,
h.name,
h.address,
h.city,
h.state,
h.phone,
h.email,
h.totalBeds
)
FROM Doctor d
JOIN d.specialization s
JOIN d.hospital h
WHERE d.id = :doctorId
""")
    DoctorProfileResponse getDoctorProfile(Long doctorId);






}