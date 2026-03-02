package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.request.PatientRequest;
import nimblix.in.HealthCareHub.response.PatientResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public String registerPatient(PatientRequest request) {
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setAge(request.getAge());
        patient.setGender(request.getGender());
        patient.setPhone(request.getPhone());
        patient.setDisease(request.getDisease());
        patient.setHospital(hospital);

        patientRepository.save(patient);
        return "Patient registered successfully";
    }

    @Override
    public List<PatientResponse> getAllPatients(Long hospitalId, String name) {
        List<Patient> patients;

        if (hospitalId != null) {
            patients = patientRepository.findByHospitalId(hospitalId);
        } else if (name != null && !name.isEmpty()) {
            patients = patientRepository.findByNameContainingIgnoreCase(name);
        } else {
            patients = patientRepository.findAll();
        }

        return patients.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponse getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return mapToResponse(patient);
    }

    private PatientResponse mapToResponse(Patient patient) {
        PatientResponse response = new PatientResponse();
        response.setId(patient.getId());
        response.setName(patient.getName());
        response.setAge(patient.getAge());
        response.setGender(patient.getGender());
        response.setPhone(patient.getPhone());
        response.setDisease(patient.getDisease());
        response.setHospitalName(patient.getHospital() != null ? patient.getHospital().getName() : null);
        return response;
    }
}
