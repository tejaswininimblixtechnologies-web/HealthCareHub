package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.MedicalRecord;
import nimblix.in.HealthCareHub.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/medical-records")
    public class MedicalRecordController {

        @Autowired
        private MedicalRecordService medicalRecordService;

        // ✅ Create Medical Record
        @PostMapping
        public MedicalRecord createRecord(@RequestBody MedicalRecord record) {
            return medicalRecordService.createRecord(record);
        }

        // ✅ Get All Records
        @GetMapping
        public List<MedicalRecord> getAllRecords() {
            return medicalRecordService.getAllRecords();
        }

        // ✅ Get Record By ID
        @GetMapping("/{id}")
        public MedicalRecord getRecordById(@PathVariable Long id) {
            return medicalRecordService.getRecordById(id);
        }

        // ✅ Update Record
        @PutMapping("/{id}")
        public MedicalRecord updateRecord(
                @PathVariable Long id,
                @RequestBody MedicalRecord record) {

            return medicalRecordService.updateRecord(id, record);
        }

        // ✅ Delete Record
        @DeleteMapping("/{id}")
        public String deleteRecord(@PathVariable Long id) {
            medicalRecordService.deleteRecord(id);
            return "Medical record deleted successfully";
        }
    }

