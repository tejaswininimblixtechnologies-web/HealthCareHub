package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.request.PatientRegistrationRequest;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public String registerPatient(PatientRegistrationRequest request) {

        // Check if email already exists
        if (patientRepository.findByEmailId(request.getEmail()).isPresent()) {
            return "Patient already exists with this email";
        }

        // Fetch Hospital
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        // Create Patient
        Patient patient = new Patient();

        patient.setName(request.getName());
        patient.setAge(request.getAge());
        patient.setGender(request.getGender());
        patient.setPhone(request.getPhone());
        patient.setDisease(request.getDisease());
        patient.setEmailId(request.getEmail());
        patient.setHospital(hospital);

        patientRepository.save(patient);

        return "Patient Registered Successfully";
    }


    @Override
    public ResponseEntity<?> getPatientDetails(Long patientId, Long hospitalId) {

        Patient patient = patientRepository
                .findByIdAndHospitalId(patientId, hospitalId)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found in this hospital"));

        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }


    @Override
    public String updatePatientDetails(PatientRegistrationRequest request) {

        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patientRepository.findByEmailId(request.getEmail())
                .filter(existingPatient -> !existingPatient.getId().equals(patient.getId()))
                .ifPresent(existingPatient -> {
                    throw new RuntimeException("Email already used by another patient");
                });

        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        patient.setName(request.getName());
        patient.setAge(request.getAge());
        patient.setGender(request.getGender());
        patient.setPhone(request.getPhone());
        patient.setDisease(request.getDisease());
        patient.setEmailId(request.getEmail());
        patient.setHospital(hospital);

        patientRepository.save(patient);

        return "Patient Updated Successfully";
    }

    @Override
    public String deletePatientDetails(Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found with id: " + patientId));

        if (!patient.getIsActive()) {
            throw new RuntimeException("Patient already deleted");
        }

        // Soft delete
        patient.setIsActive(false);
        patientRepository.save(patient);

        return "Patient deleted successfully";
    }
}
