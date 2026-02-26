package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
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

        // 🔹 1. Validate required fields
        if (request.getTestName() == null || request.getTestName().isBlank()) {
            throw new RuntimeException(HealthCareConstants.TEST_NAME_REQUIRED);
        }

        if (request.getResultValue() == null || request.getResultValue().isBlank()) {
            throw new RuntimeException(HealthCareConstants.RESULT_VALUE_REQUIRED);
        }

        if (request.getStatus() == null || request.getStatus().isBlank()) {
            throw new RuntimeException(HealthCareConstants.LAB_STATUS_REQUIRED);
        }

        if (request.getPatientId() == null) {
            throw new RuntimeException(HealthCareConstants.PATIENT_ID_REQUIRED);
        }

        if (request.getDoctorId() == null) {
            throw new RuntimeException(HealthCareConstants.DOCTOR_ID_REQUIRED);
        }

        // 🔹 2. Fetch Patient
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() ->
                        new RuntimeException(HealthCareConstants.PATIENT_NOT_FOUND)
                );

        // 🔹 3. Fetch Doctor
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() ->
                        new RuntimeException(HealthCareConstants.DOCTOR_NOT_FOUND)
                );

        // 🔥 4. Hospital Validation
        if (patient.getHospital() == null || doctor.getHospital() == null) {
            throw new RuntimeException(HealthCareConstants.HOSPITAL_DETAILS_MISSING);
        }

        if (!doctor.getHospital().getId()
                .equals(patient.getHospital().getId())) {
            throw new RuntimeException(HealthCareConstants.HOSPITAL_MISMATCH);
        }

        // 🔹 5. Create LabResult Entity
        LabResult labResult = LabResult.builder()
                .testName(request.getTestName())
                .resultValue(request.getResultValue())
                .status(request.getStatus())
                .patient(patient)
                .doctor(doctor)
                .build();

        // 🔹 6. Save to Database
        LabResult saved = labResultRepository.save(labResult);

        // 🔹 7. Map Entity → Response DTO
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