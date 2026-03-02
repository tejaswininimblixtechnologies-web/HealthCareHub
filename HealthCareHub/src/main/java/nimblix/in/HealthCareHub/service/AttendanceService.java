package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Attendance;

import java.util.List;

public interface AttendanceService {

    Attendance markAttendance(Long staffId, Attendance attendance);

    List<Attendance> getAllAttendance();
}





