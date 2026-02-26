package nimblix.in.HealthCareHub.exception;

import org.springframework.http.HttpStatus;

public class PaymentException extends BaseException {

    public PaymentException(String message) {
        super(message, HttpStatus.BAD_REQUEST, ErrorCode.PAYMENT_FAILED);
    }

    public PaymentException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.BAD_REQUEST, errorCode);
    }
}
