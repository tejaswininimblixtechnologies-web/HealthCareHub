package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByBranchId(Long branchId);

}




