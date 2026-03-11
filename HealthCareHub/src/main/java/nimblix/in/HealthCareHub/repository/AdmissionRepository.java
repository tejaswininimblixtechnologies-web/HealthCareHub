package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Admission;
import nimblix.in.HealthCareHub.response.AdmissionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {


    @Query("""
           SELECT new nimblix.in.HealthCareHub.response.AdmissionResponse(
               a.id,
               p.name,
               h.name,
               a.reason,
               a.roomNumber,
               a.admissionDate
           )
           FROM Admission a
           JOIN a.patient p
           JOIN a.hospital h
           WHERE p.id = :patientId
           """)
    List<AdmissionResponse> getAdmissionHistory(Long patientId);
}

