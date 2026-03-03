package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.response.MedicineResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    @Query("""
        SELECT new nimblix.in.HealthCareHub.response.MedicineResponse(
            m.id,
            m.medicineName,
            m.manufacturer,
            m.price,
            m.stockQuantity,
            m.hospital.id
        )
        FROM Medicine m
        WHERE m.hospital.id = :hospitalId
    """)
    List<MedicineResponse> findMedicinesByHospital(Long hospitalId);


    @Query("""
        SELECT new nimblix.in.HealthCareHub.response.MedicineResponse(
            m.id,
            m.medicineName,
            m.manufacturer,
            m.price,
            m.stockQuantity,
            m.hospital.id
        )
        FROM Medicine m
        WHERE m.stockQuantity <= :limit
    """)
    List<MedicineResponse> findLowStockMedicines(Integer limit);



    Optional<Medicine> findByMedicineNameAndHospital(String medicineName, Hospital hospital);
}