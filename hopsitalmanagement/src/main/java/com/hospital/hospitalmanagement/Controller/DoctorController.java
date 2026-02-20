package com.hospital.hospitalmanagement.Controller;


import com.hospital.hospitalmanagement.entity.Branch;
import com.hospital.hospitalmanagement.entity.Doctor;
import com.hospital.hospitalmanagement.repository.BranchRepository;
import com.hospital.hospitalmanagement.repository.DoctorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorRepository doctorRepository;
    private final BranchRepository branchRepository;

    public DoctorController(DoctorRepository doctorRepository,
                            BranchRepository branchRepository) {
        this.doctorRepository = doctorRepository;
        this.branchRepository = branchRepository;
    }

    // Add Doctor
    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Assign Doctor to Branch
    @PutMapping("/{doctorId}/assign/{branchId}")
    public Doctor assignDoctor(@PathVariable Long doctorId,
                               @PathVariable Long branchId) {

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Branch branch = branchRepository.findById(branchId).orElseThrow();

        doctor.setBranch(branch);
        return doctorRepository.save(doctor);
    }

    // Get All Doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}