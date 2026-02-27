package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Prescription;
import java.util.List;

public interface PrescriptionService {

    List<Prescription> getPrescriptionsByPatientId(Long patientId);
}