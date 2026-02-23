package nimblix.in.HealthCareHub.service.impl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.dto.DoctorRegisterRequest;
import nimblix.in.HealthCareHub.model.*;
import nimblix.in.HealthCareHub.repository.*;
import nimblix.in.HealthCareHub.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final SpecializationRepository specializationRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String registerDoctor(DoctorRegisterRequest request) {

        // ✅ Validate request
        if (request.getEmail() == null || request.getPassword() == null) {
            throw new RuntimeException("Email or Password cannot be null");
        }

        // ✅ Check email exists
        userRepository.findByEmail(request.getEmail())
                .ifPresent(existingUser -> {
                    throw new RuntimeException("Email already registered");
                });

        // ✅ Create User
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.DOCTOR)
                .enabled(true)
                .build();

        user = userRepository.save(user);

        // ✅ Validate Hospital Exists
        hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() ->
                        new RuntimeException("Hospital not found"));

        // ✅ Validate Specialization Exists
        specializationRepository.findById(request.getSpecializationId())
                .orElseThrow(() ->
                        new RuntimeException("Specialization not found"));

        // ✅ Create Doctor (SET IDS — NOT OBJECTS)
        Doctor doctor = Doctor.builder()
                .name(request.getName())
                .experienceYears(request.getExperienceYears())
                .phone(request.getPhone())
                .qualification(request.getQualification())
                .EmailId(request.getEmail())
                .password(user.getPassword())
                .userId(user.getId())                     // ⭐ IMPORTANT
                .hospitalId(request.getHospitalId())     // ⭐ IMPORTANT
                .specializationId(request.getSpecializationId()) // ⭐ IMPORTANT
                .build();

        doctorRepository.save(doctor);

        return "Doctor registered successfully";
    }
}