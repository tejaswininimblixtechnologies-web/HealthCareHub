package com.hospital.hospitalmanagement.Controller;

import com.hospital.hospitalmanagement.entity.Branch;
import com.hospital.hospitalmanagement.entity.Inventory;
import com.hospital.hospitalmanagement.repository.BranchRepository;
import com.hospital.hospitalmanagement.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryRepository inventoryRepository;
    private final BranchRepository branchRepository;

    public InventoryController(InventoryRepository inventoryRepository,
                               BranchRepository branchRepository) {
        this.inventoryRepository = inventoryRepository;
        this.branchRepository = branchRepository;
    }

    // Add Inventory to Branch
    @PostMapping("/{branchId}")
    public Inventory addInventory(@PathVariable Long branchId,
                                  @RequestBody Inventory inventory) {

        Branch branch = branchRepository.findById(branchId).orElseThrow();
        inventory.setBranch(branch);

        return inventoryRepository.save(inventory);
    }

    // Get Inventory by Branch
    @GetMapping("/branch/{branchId}")
    public List<Inventory> findByBranchId(Long branchId) {
        return inventoryRepository.findByBranchId(branchId);
    }

    // Update Inventory Quantity
    @PutMapping("/{inventoryId}")
    public Inventory updateQuantity(@PathVariable Long inventoryId,
                                    @RequestBody Inventory updatedInventory) {

        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow();
        inventory.setQuantity(updatedInventory.getQuantity());

        return inventoryRepository.save(inventory);
    }
}