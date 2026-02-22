package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentService {
    List<Appointment> filterAppointments(Long doctorId,
                                         LocalDate date,
                                         String status);
    List<LocalTime> getAvailableSlots(Long doctorId, LocalDate date);
}