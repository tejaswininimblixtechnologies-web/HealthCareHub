package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.exception.DoctorNotFoundException;
import nimblix.in.HealthCareHub.exception.UserNotFoundException;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.repository.DoctorRepository;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.response.DoctorResponse;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.HttpStatus;
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
     try {
         // Check if email already exists
         if (doctorRepository.findByEmailId(request.getDoctorEmail()).isPresent()) {
             return "Doctor already exists with this email";
         }

         // Fetch Hospital
         Hospital hospital = hospitalRepository.findById(request.getHospitalId())
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
         doctor.setQualification(request.getQualification());
         doctor.setExperienceYears(request.getExperience());
         doctor.setDescription(request.getDescription());
         doctor.setHospital(hospital);

         // ✅ CORRECT WAY (Set Objects, not IDs)
         doctor.setHospital(hospital);
         doctor.setSpecialization(specialization);

         doctorRepository.save(doctor);

         return "Doctor Registered Successfully";
      }catch (UserNotFoundException e){
         return  "User not found";
     }
    }

    @Override
    public ResponseEntity<?> getDoctorDetails(Long doctorId, Long hospitalId) {

        Doctor doctor = doctorRepository
                .findByIdAndHospitalId(doctorId, hospitalId)
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found in this hospital"));

        return ResponseEntity.status(HttpStatus.OK).body(doctor);
    }

    @Override
    public String updateDoctorDetails(DoctorRegistrationRequest request) {

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctorRepository.findByEmailId(request.getDoctorEmail())
                .filter(existingDoctor -> !existingDoctor.getId().equals(doctor.getId()))
                .ifPresent(existingDoctor -> {
                    throw new RuntimeException("Email already used by another doctor");
                });

        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        Specialization specialization = specializationRepository
                .findByName(request.getSpecializationName())
                .orElseThrow(() -> new RuntimeException("Specialization not found"));

        doctor.setName(request.getDoctorName());
        doctor.setEmailId(request.getDoctorEmail());
        doctor.setPassword(request.getPassword());
        doctor.setPhone(request.getPhoneNo());
        doctor.setQualification(request.getQualification());
        doctor.setExperienceYears(request.getExperience());
        doctor.setDescription(request.getDescription());

        doctor.setHospital(hospital);
        doctor.setSpecialization(specialization);

        doctorRepository.save(doctor);

        return "Doctor Updated Successfully";
    }

//    @Override
//    public String deleteDoctorDetails(Long doctorId) {
//
//        Doctor doctor = doctorRepository.findById(doctorId)
//                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));
//
//        doctorRepository.delete(doctor);
//
//        return "Doctor deleted successfully (Hard Delete)";
//    }


    @Override
    public String deleteDoctorDetails(Long doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));

        doctor.setIsActive(HealthCareConstants.IN_ACTIVE);
        doctorRepository.save(doctor);

        return "Doctor deleted successfully (Hard Delete)";
    }
    @Override
    public DoctorProfileResponse getDoctorProfile(Long doctorId) {

        return doctorRepository.findDoctorProfileById(doctorId)
                .orElseThrow(() ->
                        new DoctorNotFoundException("Doctor not found with id: " + doctorId)
                );
    }



    @Override
    public List<DoctorResponse> getAllDoctors(Long specializationId, Long hospitalId, String city, String name) {
        List<Doctor> doctors;

        if (specializationId != null) {
            doctors = doctorRepository.findBySpecializationId(specializationId);
        } else if (hospitalId != null) {
            doctors = doctorRepository.findByHospitalId(hospitalId);
        } else if (city != null && !city.isEmpty()) {
            doctors = doctorRepository.findByHospitalCity(city);
        } else if (name != null && !name.isEmpty()) {
            doctors = doctorRepository.findByNameContainingIgnoreCase(name);
        } else {
            doctors = doctorRepository.findAll();
        }

        return doctors.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private DoctorResponse mapToResponse(Doctor doctor) {
        DoctorResponse response = new DoctorResponse();
        response.setId(doctor.getId());
        response.setName(doctor.getName());
        response.setExperienceYears(doctor.getExperienceYears());
        response.setPhone(doctor.getPhone());
        response.setEmailId(doctor.getEmailId());
        response.setQualification(doctor.getQualification());
        response.setHospitalName(doctor.getHospital().getName());
        response.setSpecializationName(doctor.getSpecialization().getName());
        return response;
    }

}

