package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.dto.DoctorRegisterRequest;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Specialization;
import nimblix.in.HealthCareHub.repository.DoctorRepository;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.SpecializationRepository;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final SpecializationRepository specializationRepository;

    @Override
    public Doctor registerDoctor(DoctorRegisterRequest request) {

        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        Specialization specialization = specializationRepository.findById(request.getSpecializationId())
                .orElseThrow(() -> new RuntimeException("Specialization not found"));

        Doctor doctor = Doctor.builder()
                .name(request.getName())
                .experienceYears(request.getExperienceYears())
                .phone(request.getPhoneNo())
                .qualification(request.getQualification())
                .consultationFee(request.getConsultationFee())
                .hospital(hospital)
                .specialization(specialization)
                .build();

        return doctorRepository.save(doctor);
    }
}
