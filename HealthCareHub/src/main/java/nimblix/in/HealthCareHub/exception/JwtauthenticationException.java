package nimblix.in.HealthCareHub.exception;

import org.springframework.http.HttpStatus;

public class JwtauthenticationException extends BaseException {
    public JwtauthenticationException(String message) {
        super(message, HttpStatus.UNAUTHORIZED, ErrorCode.UNAUTHORIZED);
    }
}
