


    package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.dto.AdmissionHistoryResponse;

import java.util.List;

    public interface AdmissionService {

        List<AdmissionHistoryResponse> getAdmissionHistory(Long patientId);

    }

