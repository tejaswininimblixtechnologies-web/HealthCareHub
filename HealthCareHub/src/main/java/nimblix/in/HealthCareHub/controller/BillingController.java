package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.response.BillingHistoryResponse;
import nimblix.in.HealthCareHub.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/billing")
@CrossOrigin(origins = "*")
public class BillingController {

    @Autowired
    private BillingService billingService;

    /**
     * Get complete billing history for a patient
     * GET /api/billing/history/{patientId}
     */
    @GetMapping("/history/{patientId}")
    public ResponseEntity<?> getBillingHistory(@PathVariable Long patientId) {
        try {
            List<BillingHistoryResponse> billingHistory = billingService.getBillingHistoryByPatientId(patientId);

            if (billingHistory == null || billingHistory.isEmpty()) {
                Map<String, Object> emptyResponse = new HashMap<>();
                emptyResponse.put("success", false);
                emptyResponse.put("message", "No billing history found for patientId: " + patientId);
                emptyResponse.put("data", billingHistory);
                emptyResponse.put("totalRecords", 0);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(emptyResponse);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Billing history retrieved successfully");
            response.put("data", billingHistory);
            response.put("totalRecords", billingHistory.size());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error fetching billing history: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get billing history filtered by payment status
     * GET /api/billing/history/{patientId}/status/{status}
     */
    @GetMapping("/history/{patientId}/status/{status}")
    public ResponseEntity<?> getBillingHistoryByStatus(
            @PathVariable Long patientId,
            @PathVariable String status) {
        try {
            List<BillingHistoryResponse> billingHistory = billingService.getBillingHistoryByPatientIdAndStatus(patientId, status.toUpperCase());

            if (billingHistory == null || billingHistory.isEmpty()) {
                Map<String, Object> emptyResponse = new HashMap<>();
                emptyResponse.put("success", false);
                emptyResponse.put("message", "No billing history found for patientId: " + patientId + " with status: " + status);
                emptyResponse.put("data", billingHistory);
                emptyResponse.put("totalRecords", 0);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(emptyResponse);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Billing history with status " + status + " retrieved successfully");
            response.put("data", billingHistory);
            response.put("totalRecords", billingHistory.size());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error fetching billing history: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get total billing amount for a patient
     * GET /api/billing/total/{patientId}
     */
    @GetMapping("/total/{patientId}")
    public ResponseEntity<?> getTotalBillingAmount(@PathVariable Long patientId) {
        try {
            Double totalAmount = billingService.getTotalBillingAmountByPatientId(patientId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Total billing amount retrieved successfully");
            response.put("patientId", patientId);
            response.put("totalAmount", totalAmount);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error fetching total billing: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Get successful payments total for a patient
     * GET /api/billing/successful-total/{patientId}
     */
    @GetMapping("/successful-total/{patientId}")
    public ResponseEntity<?> getSuccessfulPaymentsTotal(@PathVariable Long patientId) {
        try {
            Double successfulTotal = billingService.getSuccessfulPaymentsTotalByPatientId(patientId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Successful payments total retrieved successfully");
            response.put("patientId", patientId);
            response.put("successfulPaymentsTotal", successfulTotal);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error fetching successful payments: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
