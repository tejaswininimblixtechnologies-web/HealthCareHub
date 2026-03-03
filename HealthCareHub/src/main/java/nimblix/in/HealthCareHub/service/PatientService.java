package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.CreateMedicalRecordRequest;
import nimblix.in.HealthCareHub.response.MedicalRecordResponse;

import java.util.List;

public interface PatientService {

    MedicalRecordResponse createMedicalRecord(CreateMedicalRecordRequest request);

    MedicalRecordResponse updateDiagnosis(Long appointmentId, String diagnosis);

    MedicalRecordResponse addTreatment(Long appointmentId, String treatment);

    MedicalRecordResponse addClinicalNotes(Long appointmentId, String notes);

    List<MedicalRecordResponse> getMedicalTimeline(Long patientId);
}