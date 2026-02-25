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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LabResultServiceImpl implements LabResultService {

    private final LabResultRepository labResultRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public LabResultResponse uploadLabResult(LabResultRequest request) {

        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        LabResult labResult = LabResult.builder()
                .testName(request.getTestName())
                .resultValue(request.getResultValue())
                .status(request.getStatus())
                .patient(patient)
                .doctor(doctor)
                .build();

        LabResult saved = labResultRepository.save(labResult);

        // 🔥 Mapping Entity → Response DTO
        return LabResultResponse.builder()
                .id(saved.getId())
                .testName(saved.getTestName())
                .resultValue(saved.getResultValue())
                .status(saved.getStatus())
                .patientId(patient.getId())
                .patientName(patient.getName())
                .doctorId(doctor.getId())
                .doctorName(doctor.getName())
                .uploadedAt(saved.getUploadedAt())
                .createdTime(saved.getCreatedTime())
                .updatedTime(saved.getUpdatedTime())
                .build();
    }
}