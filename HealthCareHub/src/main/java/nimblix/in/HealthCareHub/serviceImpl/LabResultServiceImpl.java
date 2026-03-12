package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.LabResult;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.LabResultRepository;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.response.LabResultResponse;
import nimblix.in.HealthCareHub.service.LabResultService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabResultServiceImpl implements LabResultService {

    private final LabResultRepository labResultRepository;
    private final PatientRepository patientRepository;


    @Override
    public List<LabResultResponse> getLabResultsByPatient(Long patientId) {

        // Optional validation (recommended)
        patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found with id: " + patientId));

        List<LabResultResponse> results =
                labResultRepository.findResponsesByPatientId(patientId);

        if (results.isEmpty()) {
            throw new RuntimeException("No lab results found for this patient");
        }

        return results;
    }

    // POST — Upload Lab Result (Task 213)
    @Override
    public LabResult uploadLabResult(Long patientId, LabResult labResult) {

        // Validate patient exists
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found with id: " + patientId));

        // Set patientId from path
        labResult.setPatientId(patientId);

        if (labResult.getDoctorId() == null) {
            throw new RuntimeException("DoctorId is required");
        }

        return labResultRepository.save(labResult);
    }
}