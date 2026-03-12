package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.LabResult;
import nimblix.in.HealthCareHub.response.LabResultResponse;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabResultRepository extends JpaRepository<LabResult, Long> {

    // ✅ PROJECTION QUERY (NO SERVICE MAPPING)
    @Query("""
        SELECT new nimblix.in.HealthCareHub.response.LabResultResponse(
            l.resultId,
            p.id,
            p.name,
            p.phone,
            d.id,
            d.name,
            d.specialization.name,
            l.testName,
            l.testCategory,
            l.result,
            l.referenceRange,
            l.unit,
            l.status,
            l.remarks,
            l.testedAt
        )
        FROM LabResult l
        LEFT JOIN Patient p ON p.id = l.patientId
        LEFT JOIN Doctor d ON d.id = l.doctorId
        WHERE l.patientId = :patientId
    """)
    List<LabResultResponse> findResponsesByPatientId(@Param("patientId") Long patientId);
}