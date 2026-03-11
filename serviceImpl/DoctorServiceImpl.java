package nimblix.in.HealthCareHub.serviceImpl;

import java.util.Arrays;
import java.util.List;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Specialization;
import nimblix.in.HealthCareHub.repository.DoctorRepository;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.SpecializationRepository;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.response.DoctorListResponse;
import nimblix.in.HealthCareHub.response.DoctorProfileResponse;
import nimblix.in.HealthCareHub.response.DoctorSummaryResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.service.DoctorService;

@Service
@RequiredArgsConstructor

public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final SpecializationRepository specializationRepository;



    @Override
    public List<String> getAllRoles() {

        return Arrays.stream(Role.values())
                .map(Enum::name)
                .toList();
    }

    public List<DoctorProfileResponse> filterDoctorsBySpecialization(String specialization) {

        if (specialization == null || specialization.trim().isEmpty()) {
            return null;
        }

        return doctorRepository
                .findBySpecialization_NameIgnoreCase(specialization.trim());
    }


    @Override
    public DoctorListResponse getDoctorsByHospital(Long hospitalId) {

        // Edge Case 1: hospitalId validation
        if (hospitalId == null || hospitalId <= 0) {
            throw new IllegalArgumentException(HealthCareConstants.INVALID_HOSPITAL_ID);
        }

// Query used to fetch doctors for the given hospital
        List<Doctor> doctors = doctorRepository.findDoctorsByHospitalId(hospitalId);


        // Edge Case 2: No doctors found
        if (doctors.isEmpty()) {
            return

                    new DoctorListResponse(
                    HealthCareConstants.SUCCESS,
                    HealthCareConstants.NO_DOCTORS_FOUND,
                    List.of()
            );
        }

        List<DoctorSummaryResponse> doctorResponses = doctors.stream()
                .map(doctor -> new DoctorSummaryResponse(
                        doctor.getId(),
                        doctor.getName(),
                        doctor.getSpecialization() != null
                                ? doctor.getSpecialization().getName()
                                : null
                ))
                .toList();

        return new DoctorListResponse(
                HealthCareConstants.SUCCESS,
                HealthCareConstants.DOCTORS_FETCHED_SUCCESS,
                doctorResponses
        );

    }

    @Transactional
    @Override
    public DoctorProfileResponse addDoctor(DoctorRegistrationRequest request) {

        // Edge Case 1
        if (request == null) {
            throw new RuntimeException(HealthCareConstants.INVALID_REQUEST);
        }

        // Edge Case 2
        if (doctorRepository.findByPhone(request.getPhoneNo()).isPresent()) {
            throw new RuntimeException(HealthCareConstants.DOCTOR_PHONE_EXISTS);
        }

        // Edge Case 3
        if (doctorRepository.findByEmailId(request.getDoctorEmail()).isPresent()) {
            throw new RuntimeException(HealthCareConstants.DOCTOR_EMAIL_EXISTS);
        }

        // Fetch hospital
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() ->
                        new RuntimeException(HealthCareConstants.HOSPITAL_NOT_FOUND));

        // Fetch specialization
        Specialization specialization = specializationRepository
                .findByName(request.getSpecializationName())
                .orElseThrow(() ->
                        new RuntimeException(HealthCareConstants.SPECIALIZATION_NOT_FOUND));

        // Save doctor
        Doctor doctor = Doctor.builder()
                .name(request.getDoctorName())
                .emailId(request.getDoctorEmail())
                .password(request.getPassword())
                .qualification(request.getQualification())
                .experienceYears(request.getExperience())
                .description(request.getDescription())
                .phone(request.getPhoneNo())
                .hospital(hospital)
                .specialization(specialization)
                .build();

        Doctor savedDoctor = doctorRepository.save(doctor);

        // Fetch response using SQL projection
        return doctorRepository.getDoctorProfile(savedDoctor.getId());
    }
}
