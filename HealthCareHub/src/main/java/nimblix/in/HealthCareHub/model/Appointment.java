package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ patientId field added
    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "appointment_date_time")
    private LocalDateTime appointmentDateTime;

    @Column(name = "status")
    private String status;

    // ✅ id getter setter
    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }

    // ✅ patientId getter setter
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    // ✅ doctorId getter setter
    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    // ✅ appointmentDateTime getter setter
    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }
    public void setAppointmentDateTime(LocalDateTime dt) {
        this.appointmentDateTime = dt;
    }

    // ✅ status getter setter
    public String getStatus() { return status; }
    public void setStatus(String status) {
        this.status = status;
    }
}