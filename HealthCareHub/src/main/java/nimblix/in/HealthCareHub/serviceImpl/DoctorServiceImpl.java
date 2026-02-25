package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.repository.DoctorRepository;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Specialization;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.SpecializationRepository;

import java.util.List;


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
    public Doctor getDoctorDetails(Long doctorId, Long hospitalId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        if (!doctor.getHospital().getId().equals(hospitalId)) {
            throw new RuntimeException("Doctor does not belong to this hospital");
        }

        return doctor;
    }

    @Override
    public String addPrescription(Long doctorId, List<String> medicines) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.getPrescriptions().addAll(medicines);

        doctorRepository.save(doctor);

        return "Prescription added successfully";
    }

    @Override
    public List<String> getPrescriptions(Long doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        return doctor.getPrescriptions();
    }
}