package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.dto.AdmissionHistoryResponse;
import nimblix.in.HealthCareHub.exception.ResourceNotFoundException;
import nimblix.in.HealthCareHub.model.Admission;
import nimblix.in.HealthCareHub.repository.AdmissionRepository;
import nimblix.in.HealthCareHub.service.AdmissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdmissionServiceImpl implements AdmissionService {

    private final AdmissionRepository admissionRepository;

    @Override
    public List<AdmissionHistoryResponse> getAdmissionHistory(Long patientId) {

        List<Admission> admissions =
                admissionRepository.findByPatientIdOrderByAdmissionDateDesc(patientId);

        if (admissions.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No admission history found for patient id: " + patientId);
        }

        return admissions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AdmissionHistoryResponse convertToDTO(Admission admission) {

        return AdmissionHistoryResponse.builder()
                .admissionId(admission.getId())
                .admissionDate(admission.getAdmissionDate())
                .dischargeDate(admission.getDischargeDate())
                .status(admission.getStatus())
                .patientId(admission.getPatient().getId())
                .patientName(admission.getPatient().getName())
                .doctorId(admission.getDoctor() != null ?
                        admission.getDoctor().getId() : null)
                .doctorName(admission.getDoctor() != null ?
                        admission.getDoctor().getName() : null)
                .hospitalId(admission.getHospital() != null ?
                        admission.getHospital().getId() : null)
                .hospitalName(admission.getHospital() != null ?
                        admission.getHospital().getName() : null)
                .build();
    }

}
