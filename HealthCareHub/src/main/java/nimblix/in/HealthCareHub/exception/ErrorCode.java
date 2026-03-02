package nimblix.in.HealthCareHub.exception;

public enum ErrorCode {
    //Resource
    RESOURCE_NOT_FOUND,
    BAD_REQUEST,
    VALIDATION_FAILED,

    //Security
    UNAUTHORIZED,
    FORBIDDEN,
    JWT_AUTH_ERROR,

    // Doctor
    DOCTOR_NOT_FOUND,
    DOCTOR_ALREADY_EXISTS,
    DOCTOR_VALIDATION_FAILED,

    //Doctor's Specialization
    SPECIALIZATION_NOT_FOUND,

    // Hospital
    HOSPITAL_NOT_FOUND,
    HOSPITAL_ALREADY_EXISTS,
    HOSPITAL_VALIDATION_FAILED,

    // User
    USER_NOT_FOUND,
    USER_ALREADY_EXISTS,

    // Admin
    ADMIN_NOT_FOUND,

    // Nurse
    NURSE_NOT_FOUND,

    //Appointment
    APPOINTMENT_NOT_FOUND,

    // Payment
    PAYMENT_FAILED,

    //System
    INTERNAL_SERVER_ERROR
}
