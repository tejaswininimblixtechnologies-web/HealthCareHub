package nimblix.in.HealthCareHub.service;


import nimblix.in.HealthCareHub.model.Admission;
import nimblix.in.HealthCareHub.repository.AdmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmissionService {

    private final AdmissionRepository admissionRepository;
    private final PatientRepository patientRepository;

    // CREATE
    public Admission create(Long patientId, Admission admission) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        admission.setPatient(patient);

        return admissionRepository.save(admission);
    }

    // READ ALL
    public List<Admission> getAll() {
        return admissionRepository.findAll();
    }

    // READ BY ID
    public Admission getById(Long id) {
        return admissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission not found"));
    }

    // UPDATE
    public Admission update(Long id, Admission updated) {

        Admission admission = getById(id);

        admission.setAdmissionDate(updated.getAdmissionDate());
        admission.setDischargeDate(updated.getDischargeDate());
        admission.setDiagnosis(updated.getDiagnosis());
        admission.setStatus(updated.getStatus());

        return admissionRepository.save(admission);
    }

    // DELETE
    public void delete(Long id) {
        admissionRepository.deleteById(id);
    }

    // MAIN FEATURE: GET ADMISSION HISTORY
    public List<Admission> getHistory(Long patientId) {
        return admissionRepository.findByPatientId(patientId);
    }
}



