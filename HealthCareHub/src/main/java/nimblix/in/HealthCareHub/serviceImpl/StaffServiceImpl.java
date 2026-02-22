package nimblix.in.HealthCareHub.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nimblix.in.HealthCareHub.model.Staff;
import nimblix.in.HealthCareHub.repository.StaffRepository;
import nimblix.in.HealthCareHub.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public Staff addStaff(Staff staff) {
        return staffRepository.save(staff);
    }
    public Staff updateStaffRole(Long id, String designation, String department) {

        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + id));

        if (designation != null) {
            staff.setDesignation(designation);
        }

        if (department != null) {
            staff.setDepartment(department);
        }

        return staffRepository.save(staff);
    }
}


