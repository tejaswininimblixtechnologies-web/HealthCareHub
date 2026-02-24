package com.hospital.hospitalmanagement.Controller;

import com.hospital.hospitalmanagement.Service.BranchService;
import com.hospital.hospitalmanagement.entity.Branch;
import com.hospital.hospitalmanagement.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService){
    this.branchService = branchService;
    }

    @PostMapping
    public Branch createBranch(@RequestBody Branch branch){
        return branchService.createBranch(branch);
    }

    @GetMapping
    public List<Branch> getAllBranches(){
        return branchService.getAllBranches();
    }

    @GetMapping("/{id}")
    public Branch getBranchById(@PathVariable Long id){
        return branchService.getBranchById(id);
    }

}