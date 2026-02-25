package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.LabResultRequest;
import nimblix.in.HealthCareHub.response.LabResultResponse;

public interface LabResultService {

    LabResultResponse uploadLabResult(LabResultRequest request);
}