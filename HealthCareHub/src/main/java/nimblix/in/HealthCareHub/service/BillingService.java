package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.response.BillingHistoryResponse;

import java.util.List;

public interface BillingService {

    /**
     * Get complete billing history for a patient
     */
    List<BillingHistoryResponse> getBillingHistoryByPatientId(Long patientId);

    /**
     * Get billing history for a patient filtered by status
     */
    List<BillingHistoryResponse> getBillingHistoryByPatientIdAndStatus(Long patientId, String status);

    /**
     * Get total billing amount for a patient
     */
    Double getTotalBillingAmountByPatientId(Long patientId);

    /**
     * Get successful payments total for a patient
     */
    Double getSuccessfulPaymentsTotalByPatientId(Long patientId);
}

