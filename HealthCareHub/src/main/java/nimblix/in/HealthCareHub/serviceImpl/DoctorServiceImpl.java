package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.*;
import nimblix.in.HealthCareHub.repository.*;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;
    private final SpecializationRepository specializationRepository;
    private final PasswordEncoder passwordEncoder;

    // REGISTER DOCTOR
    @Override
    public String registerDoctor(DoctorRegistrationRequest request) {

        userRepository.findByEmail(request.getDoctorEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("Email already registered");
                });

        User user = userRepository.save(
                User.builder()
                        .email(request.getDoctorEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Role.DOCTOR)
                        .enabled(true)
                        .build()
        );

        Hospital hospital = hospitalRepository
                .findByName(request.getHospitalName())
                .orElseThrow(() ->
                        new RuntimeException("Hospital not found"));

        Specialization specialization = specializationRepository
                .findByName(request.getSpecializationName())
                .orElseThrow(() ->
                        new RuntimeException("Specialization not found"));

        Doctor doctor = Doctor.builder()
                .name(request.getDoctorName())
                .emailId(request.getDoctorEmail())
                .phoneNo(request.getPhoneNo())
                .experienceYears(request.getExperienceYears())   // ✅ ADD
                .qualification(request.getQualification())       // ✅ ADD
                .password(user.getPassword())
                .user(user)
                .hospital(hospital)
                .specialization(specialization)
                .build();

        doctorRepository.save(doctor);

        return "Doctor registered successfully";
    }

    // GET DOCTOR DETAILS
    @Override
    public ResponseEntity<?> getDoctorDetails(Long doctorId, Long hospitalId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found"));

        if (!doctor.getHospital().getId().equals(hospitalId)) {
            throw new RuntimeException("Doctor not belongs to hospital");
        }

        return ResponseEntity.ok(doctor);
    }
}