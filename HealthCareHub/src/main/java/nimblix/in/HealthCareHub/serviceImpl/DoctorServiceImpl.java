package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.repository.DoctorRepository;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.response.DoctorRegistrationResponse;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Specialization;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.SpecializationRepository;


@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final SpecializationRepository specializationRepository;

    @Override
    public ResponseEntity<DoctorRegistrationResponse> registerDoctor(DoctorRegistrationRequest request) {

        if (doctorRepository.findByEmailId(request.getDoctorEmail()).isPresent()) {
            throw new RuntimeException(
                    "Doctor already exists with this email");
        }


        Hospital hospital = hospitalRepository
                .findById(request.getHospitalId())
                .orElseThrow(() ->
                        new RuntimeException("Hospital not found"));

        Specialization specialization =
                specializationRepository
                        .findById(request.getSpecializationId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Specialization not found"));

        Doctor doctor = new Doctor();

        doctor.setName(request.getDoctorName());
        doctor.setEmailId(request.getDoctorEmail());
        doctor.setPassword(request.getPassword());
        doctor.setPhone(request.getPhoneNo());
        doctor.setConsultationFee(request.getConsultationFee());
        doctor.setQualification(request.getQualification());
        doctor.setExperienceYears(request.getExperienceYears());


        // Set relationships
        doctor.setHospital(hospital);
        doctor.setSpecialization(specialization);

        Doctor savedDoctor = doctorRepository.save(doctor);

        DoctorRegistrationResponse response = new DoctorRegistrationResponse();

        response.setDoctorId(savedDoctor.getId());
        response.setDoctorName(savedDoctor.getName());
        response.setDoctorEmail(savedDoctor.getEmailId());
        response.setHospitalId(savedDoctor.getHospital().getId());
        response.setHospitalName(savedDoctor.getHospital().getName());
        response.setSpecialization(savedDoctor.getSpecialization().getName());
        response.setConsultationFee(savedDoctor.getConsultationFee());
        response.setQualification(savedDoctor.getQualification());
        response.setExperienceYears(savedDoctor.getExperienceYears());



        response.setMessage("Doctor Registered Successfully");

        return ResponseEntity.ok(response);
    }
    @Override
    public ResponseEntity<?> getDoctorDetails(Long doctorId,
                                              Long hospitalId) {

        Doctor doctor = doctorRepository
                .findById(doctorId)
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found"));

        if (!doctor.getHospital().getId().equals(hospitalId)) {
            throw new RuntimeException("Doctor not belongs to hospital");
        }

        return ResponseEntity.ok(doctor);
    }
}

