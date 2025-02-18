package com.example.demo.exception;

import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.exception.FieldErrorDto;
import com.example.demo.enumeration.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiMessageDto<String>> handleAllException(Exception exception) {
        ApiMessageDto<String> response = ApiMessageDto.<String>builder()
                .result(false)
                .message("INTERNAL SERVER ERROR: " + exception.getMessage())
                .code("INTERNAL_SERVER_ERROR")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiMessageDto<String>> handleAppException(AppException exception) {
        ApiMessageDto<String> response = ApiMessageDto.<String>builder()
                .result(false)
                .message(exception.getErrorCode().getMessage())
                .code(exception.getErrorCode().getCode())
                .build();
        if (exception.getErrorCode().getCode().equals("NOT_FOUND")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiMessageDto<List<FieldErrorDto>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorCode errorCode = ErrorCode.INVALID_FORM;

        List<FieldErrorDto> errors = exception.getFieldErrors().stream()
                .map(fieldError -> new FieldErrorDto(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        ApiMessageDto<List<FieldErrorDto>> response = ApiMessageDto.<List<FieldErrorDto>>builder()
                .result(false)
                .message(errorCode.getMessage())
                .code(errorCode.getCode())
                .data(errors)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiMessageDto<String>> handleNotFoundException(NoHandlerFoundException exception) {
        ApiMessageDto<String> response = ApiMessageDto.<String>builder()
                .result(false)
                .message("The requested resource was not found: " + exception.getRequestURL())
                .code("NOT_FOUND")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiMessageDto<String>> handleResponseStatusException(ResponseStatusException exception) {
        ApiMessageDto<String> response = ApiMessageDto.<String>builder()
                .result(false)
                .message(exception.getReason())
                .code(exception.getStatusCode().toString())
                .build();
        return ResponseEntity.status(exception.getStatusCode()).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiMessageDto<String>> handleAccessDeniedException(AccessDeniedException exception) {
        ApiMessageDto<String> response = ApiMessageDto.<String>builder()
                .result(false)
                .message("FORBIDDEN: You do not have permission to access this resource.")
                .code("FORBIDDEN")
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

}
