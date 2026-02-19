package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.MedicalReport;

import java.util.List;

public interface MedicalReportService {

    List<MedicalReport> getReportsByPatient(Long patientId);

}

