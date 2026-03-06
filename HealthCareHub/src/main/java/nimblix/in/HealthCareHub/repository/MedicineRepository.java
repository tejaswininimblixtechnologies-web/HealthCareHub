package nimblix.in.HealthCareHub.repository;


import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.response.MedicineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    @Query("""
        SELECT new nimblix.in.HealthCareHub.response.MedicineResponse(
            m.id,
            m.medicineName,
            m.manufacturer,
            m.description,
            m.dosage,
            m.price,
            m.stockQuantity,
            m.isActive
        )
        FROM Medicine m
    """)
    Page<MedicineResponse> findAllMedicineResponses(Pageable pageable);
}

import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine, Long>{
        Optional<Medicine> findByMedicineNameAndHospital(String medicineName, Hospital hospital);
    }


