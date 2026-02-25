package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.PrescriptionRequest;
import nimblix.in.HealthCareHub.response.PrescriptionResponse;

import java.util.List;

public interface PrescriptionService {

    String createPrescription(PrescriptionRequest request);

    List<PrescriptionResponse> getPrescriptionsByDoctor(Long doctorId);
}