package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.AuditLog;
import nimblix.in.HealthCareHub.service.AuditLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuditLogService auditLogService;

    // Home API
    @GetMapping("/")
    public String home() {
        return "Admin Panel Running Successfully!";
    }

    // Test Audit API
    @GetMapping("/audit")
    public String test() {
        return "Audit API Running!";

    }

    // Save Audit Log
    @PostMapping("/audit/save")
    public AuditLog saveAudit(@RequestBody AuditLog auditLog) {
        return auditLogService.saveAuditLog(auditLog);
    }

    // Get All Audit Logs
    @GetMapping("/audit/all")
    public List<AuditLog> getAllLogs() {
        return auditLogService.getAllLogs();
    }
}
}
