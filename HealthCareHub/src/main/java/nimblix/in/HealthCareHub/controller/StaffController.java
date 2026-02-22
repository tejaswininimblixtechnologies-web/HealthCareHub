package nimblix.in.HealthCareHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nimblix.in.HealthCareHub.model.Staff;
import nimblix.in.HealthCareHub.service.StaffService;

@RestController
@RequestMapping("/staff")

public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/add")
    public Staff addStaff(@RequestBody Staff staff) {
        return staffService.addStaff(staff);
    }
    @PutMapping("/staff/{id}/role")
    public ResponseEntity<Staff> updateStaffRole(
            @PathVariable Long id,
            @RequestParam(required = false) String designation,
            @RequestParam(required = false) String department) {

        Staff updatedStaff = staffService.updateStaffRole(id, designation, department);

        return ResponseEntity.ok(updatedStaff);
    }

}
