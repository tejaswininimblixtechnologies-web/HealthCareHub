package nimblix.in.HealthCareHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
}
