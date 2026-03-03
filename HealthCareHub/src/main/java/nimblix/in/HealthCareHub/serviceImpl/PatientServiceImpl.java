package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.repository.AppointmentRepository;
import nimblix.in.HealthCareHub.request.CreateMedicalRecordRequest;
import nimblix.in.HealthCareHub.response.MedicalRecordResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final AppointmentRepository appointmentRepository;

    // ================= CREATE MEDICAL RECORD =================
    @Override
    public MedicalRecordResponse createMedicalRecord(
            CreateMedicalRecordRequest request) {

        Appointment appointment = appointmentRepository
                .findById(request.getAppointmentId())
                .orElseThrow(() ->
                        new RuntimeException("Appointment not found"));

        appointment.setDiagnosis(request.getDiagnosis());

        appointmentRepository.save(appointment);

        return map(appointment);
    }

    // ================= UPDATE DIAGNOSIS =================
    @Override
    public MedicalRecordResponse updateDiagnosis(
            Long appointmentId,
            String diagnosis) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new RuntimeException("Appointment not found"));

        appointment.setDiagnosis(diagnosis);

        return map(appointmentRepository.save(appointment));
    }

    // ================= ADD TREATMENT =================
    @Override
    public MedicalRecordResponse addTreatment(
            Long appointmentId,
            String treatment) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new RuntimeException("Appointment not found"));

        appointment.setTreatmentPlan(treatment);

        return map(appointmentRepository.save(appointment));
    }

    // ================= ADD CLINICAL NOTES =================
    @Override
    public MedicalRecordResponse addClinicalNotes(
            Long appointmentId,
            String notes) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new RuntimeException("Appointment not found"));

        appointment.setClinicalNotes(notes);

        return map(appointmentRepository.save(appointment));
    }

    // ================= MEDICAL TIMELINE =================
    @Override
    public List<MedicalRecordResponse> getMedicalTimeline(Long patientId) {

        return appointmentRepository
                .findByPatient_IdOrderByAppointmentDateTimeDesc(patientId)
                .stream()
                .map(this::map)
                .toList();
    }

    // ================= MAPPER =================
    private MedicalRecordResponse map(Appointment appointment) {
        return MedicalRecordResponse.builder()
                .appointmentId(appointment.getId())
                .diagnosis(appointment.getDiagnosis())
                .treatmentPlan(appointment.getTreatmentPlan())
                .clinicalNotes(appointment.getClinicalNotes())
                .appointmentDateTime(appointment.getUpdatedTime())
                .build();
    }}