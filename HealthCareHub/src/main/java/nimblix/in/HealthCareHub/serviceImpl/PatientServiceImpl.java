package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.exception.ResourceNotFoundException;
import nimblix.in.HealthCareHub.model.Admission;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.AdmissionRepository;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.request.AdmissionRequest;
import nimblix.in.HealthCareHub.request.PatientRequest;
import nimblix.in.HealthCareHub.response.AdmissionResponse;
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
    private final AdmissionRepository admissionRepository;

    @Override
    public PatientResponse addPatient(PatientRequest request) {

        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        Patient patient = Patient.builder()
                .name(request.getName())
                .age(request.getAge())
                .gender(request.getGender())
                .phone(request.getPhone())
                .disease(request.getDisease())
                .hospital(hospital)
                .build();

        Patient saved = patientRepository.save(patient);

        return mapToResponse(saved);
    }

    @Override
    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PatientResponse getPatientById(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return mapToResponse(patient);
    }

    @Override
    public PatientResponse updatePatient(Long id, PatientRequest request) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        patient.setName(request.getName());
        patient.setAge(request.getAge());
        patient.setGender(request.getGender());
        patient.setPhone(request.getPhone());
        patient.setDisease(request.getDisease());
        patient.setHospital(hospital);

        return mapToResponse(patientRepository.save(patient));
    }

    @Override
    public void deletePatient(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patientRepository.delete(patient);
    }

    // 🔥 Admission History
    @Override
    public List<AdmissionResponse> getAdmissionHistory(Long patientId) {

        List<Admission> admissions = admissionRepository.findByPatientId(patientId);

        return admissions.stream()
                .map(a -> AdmissionResponse.builder()
                        .id(a.getId())
                        .admissionDate(a.getAdmissionDate())
                        .reason(a.getReason())
                        .roomNumber(a.getRoomNumber())
                        .build())
                .toList();
    }

    private PatientResponse mapToResponse(Patient patient) {
        return PatientResponse.builder()
                .id(patient.getId())
                .name(patient.getName())
                .age(patient.getAge())
                .gender(patient.getGender())
                .phone(patient.getPhone())
                .disease(patient.getDisease())
                .build();
    }
    @Override
    public AdmissionResponse createAdmission(Long patientId, AdmissionRequest request) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found"));

        Admission admission = Admission.builder()
                .patient(patient)
                .hospital(hospital)
                .admissionDate(request.getAdmissionDate())
                .reason(request.getReason())
                .roomNumber(request.getRoomNumber())
                .build();

        Admission saved = admissionRepository.save(admission);

        return AdmissionResponse.builder()
                .id(saved.getId())
                .patientName(patient.getName())
                .hospitalName(hospital.getName())
                .admissionDate(saved.getAdmissionDate())
                .reason(saved.getReason())
                .roomNumber(saved.getRoomNumber())
                .build();
    }
}