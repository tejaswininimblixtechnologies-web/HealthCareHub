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

    // ================= MAPPER =================

    private MedicalRecordResponse map(Appointment a) {
        return MedicalRecordResponse.builder()
                .appointmentId(a.getId())
                .diagnosis(a.getDiagnosis())
                .treatmentPlan(a.getTreatmentPlan())
                .clinicalNotes(a.getClinicalNotes())
                .appointmentDateTime(a.getAppointmentDateTime())
                .build();
    }

    // ================= CREATE =================

    @Override
    public MedicalRecordResponse createMedicalRecord(
            CreateMedicalRecordRequest request) {

        Appointment appointment = appointmentRepository.findById(
                        request.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

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
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setDiagnosis(diagnosis);

        appointmentRepository.save(appointment);

        return map(appointment);
    }

    // ================= TREATMENT =================

    @Override
    public MedicalRecordResponse addTreatment(
            Long appointmentId,
            String treatment) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setTreatmentPlan(treatment);

        appointmentRepository.save(appointment);

        return map(appointment);
    }

    // ================= NOTES =================

    @Override
    public MedicalRecordResponse addClinicalNotes(
            Long appointmentId,
            String notes) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setClinicalNotes(notes);

        appointmentRepository.save(appointment);

        return map(appointment);
    }

    // ================= TIMELINE =================

    @Override
    public List<MedicalRecordResponse> getMedicalTimeline(Long patientId) {

        return appointmentRepository
                .findByPatientIdOrderByAppointmentDateTimeDesc(patientId)
                .stream()
                .map(this::map)
                .toList();
    }
}