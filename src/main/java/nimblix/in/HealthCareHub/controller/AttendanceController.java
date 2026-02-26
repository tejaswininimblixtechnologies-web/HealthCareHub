package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Attendance;
import nimblix.in.HealthCareHub.service.AttendanceService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/mark")
    public Attendance markAttendance(@RequestBody Attendance attendance) {
        return attendanceService.markAttendance(attendance);
    }

    @GetMapping("/staff/{staffId}")
    public List<Attendance> getStaffAttendance(@PathVariable Long staffId) {
        return attendanceService.getStaffAttendance(staffId);
    }

    @GetMapping("/date")
    public List<Attendance> getByDate(@RequestParam String date) {
        return attendanceService.getAttendanceByDate(LocalDate.parse(date));
    }
}
