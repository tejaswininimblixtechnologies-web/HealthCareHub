package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByHospital_City(String city);

    List<Doctor> findBySpecialization_Id(Long specializationId);
}
