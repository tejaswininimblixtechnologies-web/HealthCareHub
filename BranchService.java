package com.hospital.hospitalmanagement.Service;

import com.hospital.hospitalmanagement.entity.Branch;
import com.hospital.hospitalmanagement.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class BranchService {


    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    // Create Branch

    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    // Get All Branches

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    // Get Branch By ID

    public Branch getBranchById( Long id) {
        return branchRepository.findById(id).orElse(null);
    }

}
