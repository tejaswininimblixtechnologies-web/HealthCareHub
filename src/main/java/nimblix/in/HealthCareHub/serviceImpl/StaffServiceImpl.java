package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Staff;
import nimblix.in.HealthCareHub.repository.StaffRepository;
import nimblix.in.HealthCareHub.service.StaffService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public Staff addStaff(Staff staff) {
        return staffRepository.save(staff);

    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff markAttendance(Long staffId, boolean isPresent) {
        Staff staff = staffRepository.findById(staffId).orElseThrow();
        staff.setPresentToday(isPresent);
        return staffRepository.save(staff);
    }
}
