package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.*;
import nimblix.in.HealthCareHub.repository.*;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.response.DoctorResponse;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final SpecializationRepository specializationRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public DoctorResponse registerDoctor(DoctorRegistrationRequest request) {

        // 1️⃣ Fetch DOCTOR role
        Role doctorRole = roleRepository.findByName("DOCTOR")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // 2️⃣ Fetch Hospital
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        // 3️⃣ Fetch Specialization
        Specialization specialization = specializationRepository
                .findById(request.getSpecializationId())
                .orElseThrow(() -> new RuntimeException("Specialization not found"));

        // 4️⃣ Create User
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(doctorRole))
                .enabled(true)
                .build();

        userRepository.save(user);

        // 5️⃣ Create Doctor
        Doctor doctor = Doctor.builder()
                .name(request.getName())
                .specialization(specialization)
                .hospital(hospital)
                .user(user)
                .build();

        doctorRepository.save(doctor);

        // 6️⃣ Return Response
        return DoctorResponse.builder()
                .doctorId(doctor.getId())
                .name(doctor.getName())
                .specialization(specialization.getName())
                .email(user.getEmail())
                .hospitalId(hospital.getId())
                .build();
    }

    @Override
    public DoctorResponse getDoctorDetails(Long doctorId, Long hospitalId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        if (!doctor.getHospital().getId().equals(hospitalId)) {
            throw new RuntimeException("Doctor does not belong to this hospital");
        }

        return DoctorResponse.builder()
                .doctorId(doctor.getId())
                .name(doctor.getName())
                .specialization(doctor.getSpecialization().getName())
                .email(doctor.getUser().getEmail())
                .hospitalId(doctor.getHospital().getId())
                .build();
    }
}