package com.example.demo.exception;

import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.FieldErrorResponse;
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
    public ResponseEntity<APIResponse> handleAllException(Exception exception) {
        APIResponse response = new APIResponse();
        response.setResult(false);
        response.setMessage(exception.getMessage());
        response.setCode("ERROR");
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<APIResponse> handleAppException(AppException exception) {
        APIResponse response = new APIResponse();
        response.setResult(false);
        response.setMessage(exception.getErrorCode().getMessage());
        response.setCode(exception.getErrorCode().getCode());
        if (exception.getErrorCode().getCode().equals("NOT_FOUND")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        APIResponse response = new APIResponse();
        response.setResult(false);
        ErrorCode errorCode = ErrorCode.INVALID_FORM;
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());

        List<FieldErrorResponse> errors = exception.getFieldErrors().stream()
                .map(fieldError -> new FieldErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        response.setData(errors);
        return ResponseEntity.badRequest().body(response);
    }

}
