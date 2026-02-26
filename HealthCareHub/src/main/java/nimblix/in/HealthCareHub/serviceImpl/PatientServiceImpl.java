package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.*;
import nimblix.in.HealthCareHub.repository.*;
import nimblix.in.HealthCareHub.request.PatientRegistrationRequest;
import nimblix.in.HealthCareHub.response.PatientResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PatientResponse registerPatient(PatientRegistrationRequest request) {

        // 1️⃣ Get PATIENT role
        Role patientRole = roleRepository.findByName("PATIENT")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // 2️⃣ Get Hospital
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        // 3️⃣ Create User
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(patientRole))
                .enabled(true)
                .build();

        userRepository.save(user);

        // 4️⃣ Create Patient
        Patient patient = Patient.builder()
                .name(request.getName())
                .age(request.getAge())
                .gender(request.getGender())
                .phone(request.getPhone())
                .disease(request.getDisease())
                .hospital(hospital)
                .user(user)
                .build();

        patientRepository.save(patient);

        // 5️⃣ Return Response
        return PatientResponse.builder()
                .patientId(patient.getId())
                .name(patient.getName())
                .age(patient.getAge())
                .gender(patient.getGender())
                .phone(patient.getPhone())
                .disease(patient.getDisease())
                .email(user.getEmail())
                .hospitalId(hospital.getId())
                .build();
    }
}