package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.response.AppointmentResponseDTO;
import nimblix.in.HealthCareHub.exception.PatientNotFoundException;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.AppointmentRepository;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<AppointmentResponseDTO> getAppointmentsByPatient(Long patientId) {


        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(
                        "Patient not found with id: " + patientId));

        List<Appointment> appointments =
                appointmentRepository.findByPatient_Id(patientId);

        return appointments.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private AppointmentResponseDTO mapToResponse(Appointment appointment) {
        AppointmentResponseDTO response = new AppointmentResponseDTO();

        // Appointment info
        response.setAppointmentId(appointment.getId());
        response.setAppointmentDateTime(appointment.getAppointmentDateTime());
        response.setStatus(appointment.getStatus());

        // Patient info - uses Patient.id and Patient.name
        Patient patient = appointment.getPatient();
        response.setPatientId(patient.getId());
        response.setPatientName(patient.getName());
        response.setPatientPhone(patient.getPhone());

        // Doctor info - uses Doctor.id, Doctor.name, Doctor.specialization.name
        Doctor doctor = appointment.getDoctor();
        response.setDoctorId(doctor.getId());
        response.setDoctorName("Dr. " + doctor.getName());

        // Get specialization name if exists
        if (doctor.getSpecialization() != null) {
            response.setDoctorSpecialization(doctor.getSpecialization().getName());
        } else {
            response.setDoctorSpecialization("General");
        }

        return response;
    }
}
