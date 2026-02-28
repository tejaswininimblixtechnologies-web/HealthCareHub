package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.LabResultRequest;
import nimblix.in.HealthCareHub.response.LabResultResponse;

import java.util.List;

public interface LabResultService {

    LabResultResponse uploadLabResult(Long patientId, LabResultRequest request);

    List<LabResultResponse> getLabResultsByPatientId(Long patientId);
}