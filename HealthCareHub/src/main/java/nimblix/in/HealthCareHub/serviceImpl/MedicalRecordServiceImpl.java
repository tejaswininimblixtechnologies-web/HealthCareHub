package nimblix.in.HealthCareHub.serviceImpl;


import nimblix.in.HealthCareHub.model.MedicalRecord;
import nimblix.in.HealthCareHub.repository.MedicalRecordRepository;
import nimblix.in.HealthCareHub.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class MedicalRecordServiceImpl extends MedicalRecordService {

        @Autowired
        private MedicalRecordRepository repository;

        // ✅ Task 11 — Create Medical Record
        @Override
        public MedicalRecord createRecord(MedicalRecord record) {
            return repository.save(record);
        }

        // ✅ Task 12 — Update Diagnosis
        @Override
        public MedicalRecord updateDiagnosis(Long id, String diagnosis) {

            MedicalRecord record = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Medical record not found"));

            record.setDiagnosis(diagnosis);

            return repository.save(record);
        }

        // ✅ Task 13 — Add Treatment Plan
        @Override
        public MedicalRecord addTreatmentPlan(Long id, String plan) {

            MedicalRecord record = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Medical record not found"));

            record.setTreatmentPlan(plan);

            return repository.save(record);
        }

        // ✅ Task 14 — Attach Clinical Notes
        @Override
        public MedicalRecord addClinicalNotes(Long id, String notes) {

            MedicalRecord record = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Medical record not found"));

            record.setClinicalNotes(notes);

            return repository.save(record);
        }

        // ✅ Task 15 — View Medical Timeline (All Records)
        @Override
        public List<MedicalRecord> getAllRecords() {
            return repository.findAll();
        }

        // ⭐ Get Single Record by ID
        @Override
        public MedicalRecord getRecordById(Long id) {
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Medical record not found"));
        }

        // ⭐ Delete Record
        @Override
        public void deleteRecord(Long id) {
            repository.deleteById(id);
        }
    }

