package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.response.AdmissionDischargeActivityResponse;
import nimblix.in.HealthCareHub.response.SurgeryEmergencyActivityResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    // Get only active patients
//    List<Patient> findByIsDeletedFalse();

    @Query("""
        SELECT COUNT(p) FROM Patient p
        WHERE p.hospital.id = :hospitalId
        """)
    Long countPatientsByHospitalId(@Param("hospitalId") Long hospitalId);






    // We use JPQL constructor projection to directly map results
// to AdmissionDischargeActivityResponse DTO.
    @Query("""
SELECT new nimblix.in.HealthCareHub.response.AdmissionDischargeActivityResponse(
p.admissionDate,
COUNT(p.id),
SUM(CASE WHEN p.dischargeDate IS NOT NULL THEN 1 ELSE 0 END)
)
FROM Patient p
WHERE p.admissionDate >= :startDate
GROUP BY p.admissionDate
ORDER BY p.admissionDate
""")
    List<AdmissionDischargeActivityResponse> getAdmissionsDischargesLast14Days(
            @Param("startDate") LocalDate startDate);
/*
     Query used to fetch admissions and discharges activity for the last 14 days
     This query aggregates patient data to generate dashboard chart data
     We calculate:
     1️⃣ Number of admissions per day
     2️⃣ Number of discharges per day
     3️⃣ Only for last 14 days */



    /*Query used to fetch surgeries and emergency cases for dashboard bar chart
    Aggregates patient records for the last 14 days */
    @Query("""
    SELECT new nimblix.in.HealthCareHub.response.SurgeryEmergencyActivityResponse(
    p.admissionDate,
    SUM(CASE WHEN p.surgeryRequired = true THEN 1 ELSE 0 END),
    SUM(CASE WHEN p.emergencyCase = true THEN 1 ELSE 0 END)
    )
    FROM Patient p
    WHERE p.admissionDate >= :startDate
    GROUP BY p.admissionDate
    ORDER BY p.admissionDate
    """)
    List<SurgeryEmergencyActivityResponse> getSurgeriesEmergenciesLast14Days(
            @Param("startDate") LocalDate startDate);
}
