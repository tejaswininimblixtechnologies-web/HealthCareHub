package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.model.LabResult;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.DoctorRepository;
import nimblix.in.HealthCareHub.repository.LabResultRepository;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.request.LabResultRequest;
import nimblix.in.HealthCareHub.response.LabResultResponse;
import nimblix.in.HealthCareHub.service.LabResultService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LabResultServiceImpl implements LabResultService {

    private final LabResultRepository labResultRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    // ✅ Upload Lab Result
    @Override
    public LabResultResponse uploadLabResult(Long patientId, LabResultRequest request) {

        // 🔹 Validate fields
        if (request.getTestName() == null || request.getTestName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Test Name is required");
        }

        if (request.getResultValue() == null || request.getResultValue().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Result Value is required");
        }

        if (request.getStatus() == null || request.getStatus().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status is required");
        }

        if (request.getDoctorId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor ID is required");
        }

        // 🔹 Fetch Patient
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Patient not found with id: " + patientId));

        // 🔹 Fetch Doctor
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Doctor not found with id: " + request.getDoctorId()));

        // 🔹 Hospital validation (Edge case)
        if (!patient.getHospital().getId().equals(doctor.getHospital().getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Doctor and Patient belong to different hospitals");
        }

        // 🔹 Create LabResult entity
        LabResult labResult = LabResult.builder()
                .testName(request.getTestName())
                .resultValue(request.getResultValue())
                .status(request.getStatus())
                .patient(patient)
                .doctor(doctor)
                .build();

        LabResult saved = labResultRepository.save(labResult);

        return mapToResponse(saved);
    }

    // ✅ Get Lab Results By Patient
    @Override
    public List<LabResultResponse> getLabResultsByPatientId(Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Patient not found"));

        List<LabResult> results = labResultRepository.findByPatientId(patientId);

        if (results.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No lab results found for this patient");
        }

        return results.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // 🔹 Mapping Method
    private LabResultResponse mapToResponse(LabResult labResult) {

        return LabResultResponse.builder()
                .id(labResult.getId())
                .testName(labResult.getTestName())
                .resultValue(labResult.getResultValue())
                .status(labResult.getStatus())
                .patientId(labResult.getPatient().getId())
                .patientName(labResult.getPatient().getName())
                .doctorId(labResult.getDoctor().getId())
                .doctorName(labResult.getDoctor().getName())
                .uploadedAt(labResult.getUploadedAt())
                .createdTime(labResult.getCreatedTime())
                .updatedTime(labResult.getUpdatedTime())
                .build();
    }
}