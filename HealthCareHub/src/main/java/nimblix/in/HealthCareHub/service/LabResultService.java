package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.LabResult;

public interface LabResultService {

    LabResult createLabResult(LabResult labResult);

    void notifyPatient(Long id);
}
