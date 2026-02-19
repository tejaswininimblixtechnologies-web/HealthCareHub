package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Appointment;

import java.util.List;

public interface AppointmentService {

    List<Appointment> getUpcomingAppointments(Long patientId);

}

