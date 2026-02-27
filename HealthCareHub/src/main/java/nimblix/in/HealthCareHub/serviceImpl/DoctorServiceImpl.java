package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.DoctorRepository;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.SpecializationRepository;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.response.ApiResponse;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final SpecializationRepository specializationRepository;

    @Override
    public String registerDoctor(DoctorRegistrationRequest request) {
        if (doctorRepository.findByEmailId(request.getDoctorEmail()).isPresent()) {
            return "Doctor already exists with this email";
        }

        var hospital = hospitalRepository.findByName(request.getHospitalName())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        var specialization = specializationRepository.findByName(request.getSpecializationName())
                .orElseThrow(() -> new RuntimeException("Specialization not found"));

        Doctor doctor = new Doctor();
        doctor.setName(request.getDoctorName());
        doctor.setEmailId(request.getDoctorEmail());
        doctor.setPassword(request.getPassword());
        doctor.setPhone(request.getPhoneNo());
        doctor.setHospital(hospital);
        doctor.setSpecialization(specialization);

        doctorRepository.save(doctor);

        return "Doctor Registered Successfully";
    }

    @Override
    public ResponseEntity<?> getDoctorDetails(Long doctorId, Long hospitalId) {
        // implement your logic if needed
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> activateDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        User user = doctor.getUser();
        if (user == null)
            return ResponseEntity.badRequest().body(
                    new ApiResponse("error", "No associated user for doctor", LocalDateTime.now())
            );

        user.setEnabled(true);
        doctorRepository.save(doctor);

        return ResponseEntity.ok(
                new ApiResponse("success", "Doctor activated successfully", LocalDateTime.now())
        );
    }

    @Override
    public ResponseEntity<ApiResponse> deactivateDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        User user = doctor.getUser();
        if (user == null)
            return ResponseEntity.badRequest().body(
                    new ApiResponse("error", "No associated user for doctor", LocalDateTime.now())
            );

        user.setEnabled(false);
        doctorRepository.save(doctor);

        return ResponseEntity.ok(
                new ApiResponse("success", "Doctor deactivated successfully", LocalDateTime.now())
        );
    }
}