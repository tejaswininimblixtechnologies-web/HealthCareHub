package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Attendance;
import nimblix.in.HealthCareHub.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AttendanceService attendanceService;

    // Mark attendance for staff
    @PostMapping("/attendance/{staffId}")
    public ResponseEntity<Attendance> markAttendance(
            @PathVariable Long staffId,
            @RequestBody Attendance attendance) {

        if (attendance == null || attendance.getStatus() == null) {
            throw new RuntimeException("Attendance status must not be null");
        }

        Attendance savedAttendance =
                attendanceService.markAttendance(staffId, attendance);

        return ResponseEntity.ok(savedAttendance);
    }

    // Get all attendance records
    @GetMapping("/attendance")
    public ResponseEntity<List<Attendance>> getAllAttendance() {

        List<Attendance> attendanceList =
                attendanceService.getAllAttendance();

        return ResponseEntity.ok(attendanceList);
    }
}