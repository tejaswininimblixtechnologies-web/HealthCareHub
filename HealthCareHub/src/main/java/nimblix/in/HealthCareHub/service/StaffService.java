package nimblix.in.HealthCareHub.service;
import nimblix.in.HealthCareHub.model.Staff;

public interface StaffService {
    Staff addStaff(Staff staff);
    Staff updateStaffRole(Long id, String designation, String department);

}
