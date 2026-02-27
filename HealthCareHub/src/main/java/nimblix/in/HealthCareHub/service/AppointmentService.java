package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Appointment;
import java.util.List;

public interface AppointmentService {

    Appointment bookAppointment(Appointment appointment);

    List<Appointment> getAllAppointments();   // ✅ ADD THIS
}