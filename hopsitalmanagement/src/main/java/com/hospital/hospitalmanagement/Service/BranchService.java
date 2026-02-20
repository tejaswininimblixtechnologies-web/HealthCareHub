package com.hospital.hospitalmanagement.Service;

import com.hospital.hospitalmanagement.entity.Branch;
import com.hospital.hospitalmanagement.repository.BranchRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    // Create Branch
    @PostMapping
    public Branch createBranch(@RequestBody Branch branch) {
        return branchRepository.save(branch);
    }

    // Get All Branches
    @GetMapping
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    // Get Branch By ID
    @GetMapping("/{id}")
    public Branch getBranchById(@PathVariable Long id) {
        return branchRepository.findById(id).orElseThrow();
    }

    // Delete Branch
    @DeleteMapping("/{id}")
    public String deleteBranch(@PathVariable Long id) {
        branchRepository.deleteById(id);
        return "Branch deleted successfully";
    }
}