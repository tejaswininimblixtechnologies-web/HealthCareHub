package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.AuditLog;

import java.util.List;

public interface AuditLogService {

    AuditLog saveAuditLog(AuditLog auditLog);

    List<AuditLog> getAllLogs();
}