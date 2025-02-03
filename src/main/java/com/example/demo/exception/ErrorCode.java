package com.example.demo.exception;

public enum ErrorCode {
    USER_EXITED("ERROR", "Username already exists"),
    USER_NOT_FOUND("NOT_FOUND", "User not found"),
    SUBJECT_CODE_EXITED("ERROR", "Subject code already exists"),
    SUBJECT_NOT_FOUND("NOT_FOUND", "Subject not found"),
    INVALID_FORM("ERROR", "Invalid form"),
    UNAUTHENTICATED("ERROR", "Unauthenticated"),
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
