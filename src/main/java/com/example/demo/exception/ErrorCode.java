package com.example.demo.exception;

public enum ErrorCode {
    USER_EXITED("ERROR", "Username already exists"),
    USER_NOT_FOUND("NOT_FOUND", "User not found"),
    INVALID_FORM("ERROR", "Invalid form"),
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
