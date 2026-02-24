package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.AuditLog;
import nimblix.in.HealthCareHub.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    // ✅ Constructor Injection
    public AuditLogServiceImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public AuditLog saveAuditLog(AuditLog auditLog) {
        auditLog.setCreatedAt(LocalDateTime.now());
        return auditLogRepository.save(auditLog);
    }

    @Override
    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }
}
