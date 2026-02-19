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
}
