package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    Attendance markAttendance(Attendance attendance);

    List<Attendance> getStaffAttendance(Long staffId);

    List<Attendance> getAttendanceByDate(LocalDate date);
}
