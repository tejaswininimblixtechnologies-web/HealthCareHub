package nimblix.in.HealthCareHub.serviceImpl;

<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.repository.DoctorRepository;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.service.DoctorService;
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
    public String RegisterDoctor(DoctorRegistrationRequest request) {

        Hospital hospital = hospitalRepository.findByName(request.getHospitalName())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        Specialization specialization = specializationRepository.findByName(request.getSpecialization())
                .orElseThrow(() -> new RuntimeException("Specialization not found"));

        // Create Doctor
        Doctor doctor = new Doctor();

        doctor.setName(request.getDoctorName());
        doctor.setPhone(request.getPhoneNo());
        doctor.setEmailId(request.getDoctorEmail());
        doctor.setPassword(request.getPassword());

        // Set IDs (Correct way based on your entity)
        doctor.setHospitalId(hospital.getId());
        doctor.setSpecializationId(specialization.getId());

        doctorRepository.save(doctor);

        return "Doctor Registration Successful";
    }

    @Override
    public String registerDoctor(DoctorRegistrationRequest doctorRegistrationRequest) {
        return "";
    }

    @Override
    public List<Doctor> getAllDoctors(String search, Long specializationId) {
        if (search != null && specializationId != null) {
            return doctorRepository.findByNameContainingIgnoreCaseAndSpecializationId(search, specializationId);
        } else if (search != null) {
            return doctorRepository.findByNameContainingIgnoreCase(search);
        } else if (specializationId != null) {
            return doctorRepository.findBySpecializationId(specializationId);
        }
        return doctorRepository.findAll();
    }
}

=======
public class DoctorServiceImpl {
}
>>>>>>> origin/main
