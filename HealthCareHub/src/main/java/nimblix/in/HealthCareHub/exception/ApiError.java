package nimblix.in.HealthCareHub.exception;

import lombok.Builder;
import lombok.Getter;
import nimblix.in.HealthCareHub.exception.ErrorCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Builder
public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private String traceId;
    private ErrorCode errorCode;


}
