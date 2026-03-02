package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import nimblix.in.HealthCareHub.model.Staff;
import nimblix.in.HealthCareHub.repository.StaffRepository;
import nimblix.in.HealthCareHub.service.StaffService;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public Staff addStaff(Staff staff) {


        // 1️⃣ Null object check
        if (staff == null) {
            throw new RuntimeException("Staff object cannot be null");
        }

        // 2️⃣ Name validation
        if (staff.getName() == null || staff.getName().trim().isEmpty()) {
            throw new RuntimeException("Staff name is required");
        }

        // 3️⃣ Email validation
        if (staff.getEmail() == null || staff.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email is required");
        }

        // 4️⃣ Duplicate email check
        if (staffRepository.existsByEmail(staff.getEmail())) {
            throw new RuntimeException("Staff with this email already exists");
        }

        // 5️⃣ Phone validation
        if (staff.getPhone() == null || staff.getPhone().trim().isEmpty()) {
            throw new RuntimeException("Phone number is required");
        }

        // 6️⃣ Salary validation
        if (staff.getBaseSalary() <= 0) {
            throw new RuntimeException("Base salary must be greater than 0");
        }

        // 7️⃣ Designation validation
        if (staff.getDesignation() == null || staff.getDesignation().trim().isEmpty()) {
            throw new RuntimeException("Designation is required");
        }

        // 8️⃣ Department validation
        if (staff.getDepartment() == null || staff.getDepartment().trim().isEmpty()) {
            throw new RuntimeException("Department is required");
        }

        return staffRepository.save(staff);
    }
}


