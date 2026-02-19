package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Appointment;

import java.time.LocalDateTime;

public interface AppointmentService {

    Appointment rescheduleAppointment(Long appointmentId,
                                      LocalDateTime newDateTime);
}
