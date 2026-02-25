package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.exception.AppointmentNotFoundException;
import nimblix.in.HealthCareHub.exception.SlotNotAvailableException;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.repository.AppointmentRepository;
import nimblix.in.HealthCareHub.repository.DoctorAvailabilityRepository;
import nimblix.in.HealthCareHub.repository.DoctorRepository;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;

    //  Get all doctors
    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    //  Create doctor
    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    //  Reschedule Appointment
    @Override
    public Appointment rescheduleAppointment(Long appointmentId,
                                             LocalDateTime newDateTime) {

        // 1 Check appointment exists
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment not found with id: " + appointmentId
                        )
                );

        // 2 Allow only SCHEDULED appointments
        if (!HealthCareConstants.APPOINTMENT_SCHEDULED
                .equalsIgnoreCase(appointment.getStatus())) {

            throw new IllegalStateException(
                    "Only SCHEDULED appointments can be rescheduled"
            );
        }

        // 3 Prevent past date
        if (newDateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "Cannot reschedule to past date/time"
            );
        }

        Long doctorId = appointment.getDoctor().getId();
        LocalDate newDate = newDateTime.toLocalDate();
        LocalTime newTime = newDateTime.toLocalTime();

        // 4 Check doctor availability
        doctorAvailabilityRepository
                .findByDoctorIdAndAvailableDateAndStartTimeAndEndTimeAndIsAvailableTrue(
                        doctorId,
                        newDate,
                        newTime,
                        newTime
                )
                .orElseThrow(() ->
                        new SlotNotAvailableException(
                                "Doctor not available for selected slot"
                        )
                );

        // 5 Check slot conflict
        List<Appointment> existingAppointments =
                appointmentRepository.findByDoctorIdAndAppointmentDateTime(
                        doctorId,
                        newDateTime
                );

        boolean conflict = existingAppointments.stream()
                .anyMatch(a -> !a.getId().equals(appointmentId));

        if (conflict) {
            throw new SlotNotAvailableException("Slot already booked");
        }

        // 6 Update appointment
        appointment.setAppointmentDateTime(newDateTime);

        return appointmentRepository.save(appointment);
    }
}