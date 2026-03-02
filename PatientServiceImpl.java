package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Override
    public ResponseEntity<?> updatePatient(Long patientId, Patient request) {

        Patient patient = (Patient) patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setName(request.getName());
        patient.setPhone(request.getPhone());
        patient.setAge(request.getAge());

        patientRepository.save(patient);

        return ResponseEntity.ok("Patient updated successfully");
    }


}
