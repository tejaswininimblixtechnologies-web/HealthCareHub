package nimblix.in.HealthCareHub.serviceimpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.repository.AppointmentRepository;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.repository.DoctorRepository;
import nimblix.in.HealthCareHub.request.AppointmentRequest;
import nimblix.in.HealthCareHub.response.AppointmentResponse;
import nimblix.in.HealthCareHub.service.AppointmentService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public AppointmentResponse bookAppointment(AppointmentRequest request) {

        Patient patient = patientRepository.findById(request.getPatientId()).orElse(null);
        Doctor doctor = doctorRepository.findById(request.getDoctorId()).orElse(null);

        if (patient == null || doctor == null) {
            return null;
        }

        Appointment appointment = Appointment.builder()
                .patient(patient)
                .doctor(doctor)
                .appointmentDateTime(request.getAppointmentDateTime())
                .status("BOOKED")
                .build();

        Appointment saved = appointmentRepository.save(appointment);

        return AppointmentResponse.builder()
                .appointmentId(saved.getId())
                .patientId(saved.getPatient().getId())
                .doctorId(saved.getDoctor().getId())
                .appointmentDateTime(saved.getAppointmentDateTime())
                .status(saved.getStatus())
                .build();
    }
}