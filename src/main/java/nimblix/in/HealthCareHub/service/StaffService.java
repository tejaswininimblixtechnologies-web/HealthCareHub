package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Staff;  // <-- change this
import java.util.List;

public interface StaffService {
    Staff addStaff(Staff staff);
    List<Staff> getAllStaff();
    Staff markAttendance(Long staffId, boolean isPresent);
}






