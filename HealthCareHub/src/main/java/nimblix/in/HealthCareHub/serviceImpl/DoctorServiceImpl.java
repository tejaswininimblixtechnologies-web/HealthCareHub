package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.repository.DoctorRepository;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.request.DoctorSearchRequest;
import nimblix.in.HealthCareHub.response.DoctorResponse;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Specialization;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.SpecializationRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final SpecializationRepository specializationRepository;

    @Override
    public String registerDoctor(DoctorRegistrationRequest request) {

        // Check if email already exists
        if (doctorRepository.findByEmailId(request.getDoctorEmail()).isPresent()) {
            return "Doctor already exists with this email";
        }

        // Fetch Hospital
        Hospital hospital = hospitalRepository.findByName(request.getHospitalName())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        // Fetch Specialization
        Specialization specialization = specializationRepository.findByName(request.getSpecializationName())
                .orElseThrow(() -> new RuntimeException("Specialization not found"));

        // Create Doctor
        Doctor doctor = new Doctor();

        doctor.setName(request.getDoctorName());
        doctor.setEmailId(request.getDoctorEmail());
        doctor.setPassword(request.getPassword());
        doctor.setPhone(request.getPhoneNo());

        // ✅ CORRECT WAY (Set Objects, not IDs)
        doctor.setHospital(hospital);
        doctor.setSpecialization(specialization);

        doctorRepository.save(doctor);

        return "Doctor Registered Successfully";
    }

    @Override
    public ResponseEntity<?> getDoctorDetails(Long doctorId, Long hospitalId) {
        return null;
    }

    @Override
    public List<DoctorResponse> getAllDoctors(DoctorSearchRequest request) {
        List<Doctor> doctors = doctorRepository.findAll();

        if (request.getName() != null && !request.getName().isEmpty()) {
            doctors = doctors.stream()
                    .filter(d -> d.getName().toLowerCase().contains(request.getName().toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (request.getSpecialization() != null && !request.getSpecialization().isEmpty()) {
            doctors = doctors.stream()
                    .filter(d -> d.getSpecialization().getName().equalsIgnoreCase(request.getSpecialization()))
                    .collect(Collectors.toList());
        }

        if (request.getHospitalId() != null) {
            doctors = doctors.stream()
                    .filter(d -> d.getHospital().getId().equals(request.getHospitalId()))
                    .collect(Collectors.toList());
        }

        return doctors.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private DoctorResponse mapToResponse(Doctor doctor) {
        return DoctorResponse.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .experienceYears(doctor.getExperienceYears())
                .phone(doctor.getPhone())
                .emailId(doctor.getEmailId())
                .qualification(doctor.getQualification())
                .hospitalName(doctor.getHospital().getName())
                .hospitalId(doctor.getHospital().getId())
                .specializationName(doctor.getSpecialization().getName())
                .specializationId(doctor.getSpecialization().getId())
                .build();
    }

}

