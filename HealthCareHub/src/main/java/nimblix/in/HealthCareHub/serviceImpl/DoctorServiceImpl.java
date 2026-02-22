package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.dto.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.model.DoctorAvailability;
import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.DoctorAvailabilityRepository;
import nimblix.in.HealthCareHub.repository.DoctorRepository;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorAvailabilityRepository availabilityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor registerDoctor(DoctorRegistrationRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // ✅ encoded
                .role(Role.DOCTOR)
                .enabled(true)
                .build();

        user = userRepository.save(user);

        Doctor doctor = Doctor.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .experienceYears(request.getExperienceYears())
                .qualification(request.getQualification())
                .status(HealthCareConstants.PENDING)
                .user(user)
                .build();

        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> getDoctorsByCity(String city) {
        return doctorRepository.findByHospital_City(city);
    }

    @Override
    public List<Doctor> getDoctorsBySpecialization(Long specializationId) {
        return doctorRepository.findBySpecialization_Id(specializationId);
    }

    @Override
    public List<DoctorAvailability> getDoctorAvailability(Long doctorId) {
        return availabilityRepository.findByDoctor_IdAndIsAvailableTrue(doctorId);
    }

    @Override
    public Doctor updateDoctor(Long doctorId, Doctor updatedDoctor) {

        Doctor doctor = getDoctorById(doctorId);

        // ✅ Partial update protection
        if (updatedDoctor.getName() != null)
            doctor.setName(updatedDoctor.getName());

        if (updatedDoctor.getPhone() != null)
            doctor.setPhone(updatedDoctor.getPhone());

        if (updatedDoctor.getExperienceYears() != null)
            doctor.setExperienceYears(updatedDoctor.getExperienceYears());

        if (updatedDoctor.getQualification() != null)
            doctor.setQualification(updatedDoctor.getQualification());

        if (updatedDoctor.getStatus() != null)
            validateStatus(updatedDoctor.getStatus());

        doctor.setStatus(updatedDoctor.getStatus());

        return doctorRepository.save(doctor);
    }

    @Override
    public void updateDoctorStatus(Long doctorId, String status) {

        validateStatus(status);

        Doctor doctor = getDoctorById(doctorId);
        doctor.setStatus(status);
        doctorRepository.save(doctor);
    }

    @Override
    public void updateAvailability(Long doctorId, List<DoctorAvailability> availabilityList) {

        Doctor doctor = getDoctorById(doctorId);

        // ✅ Delete old availability first
        availabilityRepository.deleteByDoctor_Id(doctorId);

        for (DoctorAvailability availability : availabilityList) {
            availability.setDoctor(doctor);
        }

        availabilityRepository.saveAll(availabilityList);
    }

    @Override
    public boolean checkEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // ✅ Status Validation Method
    private void validateStatus(String status) {

        if (!List.of("ACTIVE", "INACTIVE", "PENDING").contains(status.toUpperCase())) {
            throw new RuntimeException("Invalid status value");
        }
    }
}