package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.*;
import nimblix.in.HealthCareHub.repository.AttendanceRepository;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.service.AttendanceService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    @Override
    public Attendance markAttendance(Long staffId, Attendance attendance) {

        // 1️⃣ Check staff exists
        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        // 2️⃣ Ensure role is valid staff role
        if (staff.getRole() != Role.NURSE &&
                staff.getRole() != Role.RECEPTIONIST &&
                staff.getRole() != Role.LAB_TECHNICIAN) {
            throw new RuntimeException("Attendance can only be marked for staff roles");
        }

        LocalDate today = LocalDate.now();

        // 3️⃣ Prevent duplicate attendance
        attendanceRepository.findByStaffAndDate(staff, today)
                .ifPresent(a -> {
                    throw new RuntimeException("Attendance already marked for today");
                });

        // 4️⃣ Set relationship + date
        attendance.setStaff(staff);
        attendance.setDate(today);

        return attendanceRepository.save(attendance);

    }
}