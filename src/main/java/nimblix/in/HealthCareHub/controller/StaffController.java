package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Staff;
import nimblix.in.HealthCareHub.service.StaffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping("/add")
    public Staff addStaff(@RequestBody Staff staff) {
        return staffService.addStaff(staff);
    }

    @GetMapping("/all")
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    @PutMapping("/attendance/{id}")
    public Staff markAttendance(@PathVariable Long id,
                                @RequestParam boolean present) {
        return staffService.markAttendance(id, present);
    }
}
