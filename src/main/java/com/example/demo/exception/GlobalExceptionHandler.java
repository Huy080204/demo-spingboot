package com.example.demo.exception;

import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.exception.FieldErrorResponse;
import com.example.demo.enumeration.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<String>> handleAllException(Exception exception) {
        APIResponse<String> response = APIResponse.<String>builder()
                .result(false)
                .message(exception.getMessage())
                .code("ERROR")
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<APIResponse<String>> handleAppException(AppException exception) {
        APIResponse<String> response = APIResponse.<String>builder()
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
    public ResponseEntity<APIResponse<List<FieldErrorResponse>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorCode errorCode = ErrorCode.INVALID_FORM;

        List<FieldErrorResponse> errors = exception.getFieldErrors().stream()
                .map(fieldError -> new FieldErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        APIResponse<List<FieldErrorResponse>> response = APIResponse.<List<FieldErrorResponse>>builder()
                .result(false)
                .message(errorCode.getCode())
                .code(errorCode.getMessage())
                .data(errors)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

}
