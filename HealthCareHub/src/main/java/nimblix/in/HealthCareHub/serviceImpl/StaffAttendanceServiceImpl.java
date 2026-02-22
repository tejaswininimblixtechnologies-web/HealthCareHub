package nimblix.in.HealthCareHub.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nimblix.in.HealthCareHub.model.StaffAttendance;
import nimblix.in.HealthCareHub.repository.StaffAttendanceRepository;
import nimblix.in.HealthCareHub.service.StaffAttendanceService;

@Service
public class StaffAttendanceServiceImpl
        implements StaffAttendanceService {

    @Autowired
    private StaffAttendanceRepository attendanceRepository;

    @Override
    public StaffAttendance markAttendance(StaffAttendance attendance) {

        return attendanceRepository.save(attendance);
    }
}