// ✅ Fix 1 — Add package
package nimblix.in.HealthCareHub.service;

// ✅ Fix 2 — All correct imports
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// ✅ Fix 3 — Clean @Service annotation
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // ✅ Save - INSERT
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // ✅ Find by Patient ID - SELECT WHERE patient_id
    public List<Appointment> findByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    // ✅ Find by ID - SELECT WHERE id
    public Appointment findById(Long id) {
        return appointmentRepository
                .findById(id)
                .orElse(null);
    }

    // ✅ Delete by ID - DELETE WHERE id
    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
}