package nimblix.in.HealthCareHub.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private String getTraceId() {
        return MDC.get("traceId") != null ? MDC.get("traceId") : "N/A";
    }

    //CUSTOM EXCEPTIONS
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiError> handleBaseException(BaseException ex, HttpServletRequest request) {

        log.error("Business exception occurred", ex);

        ApiError error = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(ex.getStatus().value())
                .error(ex.getStatus().getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .traceId(getTraceId())
                .errorCode(ex.getErrorCode())
                .build();

        return new ResponseEntity<>(error, ex.getStatus());
    }

    // Validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ApiError error = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(message)
                .path(request.getRequestURI())
                .traceId(getTraceId())
                .errorCode(ErrorCode.VALIDATION_FAILED)
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    // Access denied (403)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {

        ApiError error = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(403)
                .error("Forbidden")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .traceId(getTraceId())
                .errorCode(ErrorCode.FORBIDDEN)
                .build();

        return ResponseEntity.status(403).body(error);
    }

    // Generic exception fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest request) {

        log.error("Unexpected error occurred", ex);

        ApiError error = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(500)
                .error("Internal Server Error")
                .message("Something went wrong")
                .path(request.getRequestURI())
                .traceId(getTraceId())
                .errorCode(ErrorCode.INTERNAL_SERVER_ERROR)
                .build();

        return ResponseEntity.status(500).body(error);
    }

}