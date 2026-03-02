// ✅ Package
package nimblix.in.HealthCareHub.controller;

// ✅ All imports
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // ✅ POST - Create Appointment
    // URL: POST http://localhost:9092/api/patients/1/appointments
    @PostMapping("/patients/{patientId}/appointments")
    public ResponseEntity<?> createAppointment(
            @PathVariable Long patientId,
            @RequestBody Appointment appointment) {

        appointment.setPatientId(patientId);
        Appointment saved = appointmentService.save(appointment);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    // ✅ GET ALL - Get all appointments of patient
    // URL: GET http://localhost:9092/api/patients/1/appointments
    @GetMapping("/patients/{patientId}/appointments")
    public ResponseEntity<List<Appointment>> getAppointments(
            @PathVariable Long patientId) {

        return ResponseEntity.ok(
                appointmentService.findByPatientId(patientId));
    }

    // ✅ GET BY ID - Get single appointment
    // URL: GET http://localhost:9092/api/appointments/1
    @GetMapping("/appointments/{id}")
    public ResponseEntity<?> getAppointmentById(
            @PathVariable Long id) {

        Appointment appointment = appointmentService.findById(id);
        if (appointment != null) {
            return ResponseEntity.ok(appointment);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Appointment not found: " + id);
    }

    // ✅ PUT - Update Appointment (ADDED)
    // URL: PUT http://localhost:9092/api/appointments/1
    @PutMapping("/appointments/{id}")
    public ResponseEntity<?> updateAppointment(
            @PathVariable Long id,
            @RequestBody Appointment appointment) {

        Appointment existing = appointmentService.findById(id);
        if (existing != null) {
            existing.setDoctorId(appointment.getDoctorId());
            existing.setAppointmentDateTime(
                    appointment.getAppointmentDateTime());
            existing.setStatus(appointment.getStatus());
            Appointment updated = appointmentService.save(existing);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Appointment not found: " + id);
    }

    // ✅ DELETE - Delete Appointment (ADDED)
    // URL: DELETE http://localhost:9092/api/appointments/1
    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<?> deleteAppointment(
            @PathVariable Long id) {

        Appointment existing = appointmentService.findById(id);
        if (existing != null) {
            appointmentService.delete(id);
            return ResponseEntity
                    .ok("Appointment deleted successfully!");
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Appointment not found: " + id);
    }
}