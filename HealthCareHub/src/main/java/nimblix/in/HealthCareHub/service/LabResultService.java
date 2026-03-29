package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.LabResult;
import nimblix.in.HealthCareHub.response.LabResultResponse;

import java.util.List;

public interface LabResultService {

    List<LabResultResponse> getLabResultsByPatient(Long patientId);

    LabResult uploadLabResult(Long patientId, LabResult labResult);
}