package com.example.demo.exception;

import com.example.demo.dto.APIResponseDto;
import com.example.demo.dto.exception.FieldErrorDto;
import com.example.demo.enumeration.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<APIResponseDto<String>> handleAllException(Exception exception) {
        APIResponseDto<String> response = APIResponseDto.<String>builder()
                .result(false)
                .message("INTERNAL SERVER ERROR: " + exception.getMessage())
                .code("INTERNAL_SERVER_ERROR")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<APIResponseDto<String>> handleAppException(AppException exception) {
        APIResponseDto<String> response = APIResponseDto.<String>builder()
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
    public ResponseEntity<APIResponseDto<List<FieldErrorDto>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorCode errorCode = ErrorCode.INVALID_FORM;

        List<FieldErrorDto> errors = exception.getFieldErrors().stream()
                .map(fieldError -> new FieldErrorDto(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        APIResponseDto<List<FieldErrorDto>> response = APIResponseDto.<List<FieldErrorDto>>builder()
                .result(false)
                .message(errorCode.getMessage())
                .code(errorCode.getCode())
                .data(errors)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<APIResponseDto<String>> handleNotFoundException(NoHandlerFoundException exception) {
        APIResponseDto<String> response = APIResponseDto.<String>builder()
                .result(false)
                .message("The requested resource was not found: " + exception.getRequestURL())
                .code("NOT_FOUND")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<APIResponseDto<String>> handleResponseStatusException(ResponseStatusException exception) {
        APIResponseDto<String> response = APIResponseDto.<String>builder()
                .result(false)
                .message(exception.getReason())
                .code(exception.getStatusCode().toString())
                .build();
        return ResponseEntity.status(exception.getStatusCode()).body(response);
    }

}
