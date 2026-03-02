package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.PatientDocumentUploadRequest;
import nimblix.in.HealthCareHub.response.PatientDocumentUploadResponse;

public interface PatientService {

    PatientDocumentUploadResponse uploadPatientDocument(long patientId,PatientDocumentUploadRequest request);

}
