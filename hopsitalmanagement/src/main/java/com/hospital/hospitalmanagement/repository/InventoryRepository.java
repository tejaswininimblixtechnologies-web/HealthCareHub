package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    List<Inventory> findByBranchId(Long branchId);
}