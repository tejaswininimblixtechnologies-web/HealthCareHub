package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.MedicalRecord;
import nimblix.in.HealthCareHub.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository repository;

    // Task 11 — Create Medical Record
    public MedicalRecord createRecord(MedicalRecord record) {
        return repository.save(record);
    }

    // Task 12 — Update Diagnosis
    public MedicalRecord updateDiagnosis(Long id, String diagnosis) {
        MedicalRecord record = repository.findById(id).orElseThrow();
        record.setDiagnosis(diagnosis);
        return repository.save(record);
    }

    // Task 13 — Add Treatment Plan
    public MedicalRecord addTreatmentPlan(Long id, String plan) {
        MedicalRecord record = repository.findById(id).orElseThrow();
        record.setTreatmentPlan(plan);
        return repository.save(record);
    }

    // Task 14 — Attach Clinical Notes
    public MedicalRecord addClinicalNotes(Long id, String notes) {
        MedicalRecord record = repository.findById(id).orElseThrow();
        record.setClinicalNotes(notes);
        return repository.save(record);
    }

    // Task 15 — View Timeline (All Records)
    public List<MedicalRecord> getAllRecords() {
        return repository.findAll();
    }

    // ⭐ Update Full Record
    public MedicalRecord updateRecord(Long id, MedicalRecord newRecord) {
        MedicalRecord record = repository.findById(id).orElseThrow();

        record.setDiagnosis(newRecord.getDiagnosis());
        record.setTreatmentPlan(newRecord.getTreatmentPlan());
        record.setClinicalNotes(newRecord.getClinicalNotes());

        return repository.save(record);
    }

    // ⭐ Get Single Record by ID
    public MedicalRecord getRecordById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    // ⭐ Delete Record
    public void deleteRecord(Long id) {
        repository.deleteById(id);
    }
}
