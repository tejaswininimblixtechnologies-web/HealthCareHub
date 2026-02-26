package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
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
            throw new RuntimeException(HealthCareConstants.DOCTOR_ALREADY_EXISTS);
        }

        Hospital hospital = hospitalRepository.findById(request.getHospitalId()).orElseThrow(() ->
                        new RuntimeException(HealthCareConstants.HOSPITAL_NOT_FOUND));

        Specialization specialization = specializationRepository.findById(request.getSpecializationId()).
                orElseThrow(() -> new RuntimeException(HealthCareConstants.SPECIALIZATION_NOT_FOUND));

        Doctor doctor = new Doctor();

        doctor.setName(request.getDoctorName());
        doctor.setEmailId(request.getDoctorEmail());
        doctor.setPassword(request.getPassword());
        doctor.setPhone(request.getPhoneNo());
        doctor.setConsultationFee(request.getConsultationFee());
        doctor.setQualification(request.getQualification());
        doctor.setExperienceYears(request.getExperienceYears());

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

        response.setMessage(HealthCareConstants.DOCTOR_REGISTERED_SUCCESS);

        return ResponseEntity.ok(response);
    }
    @Override
    public ResponseEntity<?> getDoctorDetails(Long doctorId, Long hospitalId) {

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() ->
                new RuntimeException(HealthCareConstants.DOCTOR_NOT_FOUND));

        if (!doctor.getHospital().getId().equals(hospitalId)) {
            throw new RuntimeException(HealthCareConstants.DOCTOR_NOT_BELONG_TO_HOSPITAL);
        }

        DoctorRegistrationResponse response = new DoctorRegistrationResponse();

        response.setDoctorId(doctor.getId());
        response.setDoctorName(doctor.getName());
        response.setDoctorEmail(doctor.getEmailId());
        response.setHospitalId(doctor.getHospital().getId());
        response.setHospitalName(doctor.getHospital().getName());
        response.setSpecialization(doctor.getSpecialization().getName());
        response.setConsultationFee(doctor.getConsultationFee());
        response.setQualification(doctor.getQualification());
        response.setExperienceYears(doctor.getExperienceYears());
        return ResponseEntity.ok(response);
    }
}

