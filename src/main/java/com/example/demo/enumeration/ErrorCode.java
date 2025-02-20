package com.example.demo.enumeration;

public enum ErrorCode {
    USER_EXITED("ERROR", "Username already exists"),
    USER_NOT_FOUND("NOT_FOUND", "User not found"),
    STUDENT_NOT_FOUND("NOT_FOUND", "Student not found"),
    ROLE_NOT_FOUND("NOT_FOUND", "Role not found"),
    SUBJECT_CODE_EXITED("ERROR", "Subject code already exists"),
    SUBJECT_NOT_FOUND("NOT_FOUND", "Subject not found"),
    INVALID_FORM("ERROR", "Invalid form"),
    UNAUTHENTICATED("ERROR", "Unauthenticated"),
    STUDENT_ALREADY_ENROLLED("ERROR", "Student already enrolled"),
    PERIOD_EXITED("ERROR", "Period already exists"),
    PERIOD_NOT_FOUND("NOT_FOUND", "Period not found"),
    LECTURER_SCHEDULER_NOT_FOUND("NOT_FOUND", "Lecturer scheduler not found"),
    LECTURER_SCHEDULER_EXITED("ERROR", "Lecturer scheduler already exists")
    ;

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
