package nimblix.in.HealthCareHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import nimblix.in.HealthCareHub.model.StaffAttendance;
import nimblix.in.HealthCareHub.service.StaffAttendanceService;

@RestController
@RequestMapping("/attendance")
public class StaffAttendanceController {

    @Autowired
    private StaffAttendanceService attendanceService;

    @PostMapping("/mark")
    public StaffAttendance markAttendance(
            @RequestBody StaffAttendance attendance) {

        return attendanceService.markAttendance(attendance);
    }
}