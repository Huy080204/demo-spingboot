package com.example.demo.exception;

import com.example.demo.dto.ApiMessageDto;
import com.example.demo.enumeration.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import io.jsonwebtoken.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomFeignErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ErrorDecoder defaultDecoder = new Default();


    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            ApiMessageDto<?> errorResponse = objectMapper.readValue(response.body().asInputStream(), ApiMessageDto.class);

            log.error("FeignClient Error: {} - {}", response.status(), errorResponse.getMessage());

            String errorResponseMessageStr = errorResponse.getMessage();
            ErrorCode errorCode = getErrorCodeFromString(errorResponseMessageStr);

            if (errorCode != null) {
                return new AppException(errorCode);
            } else {
                return new AppException(ErrorCode.UNAUTHENTICATED);
            }
        } catch (IOException e) {
            log.error("Error decoding Feign error response", e);
            return new RuntimeException("Error parsing Feign error response", e);
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ErrorCode getErrorCodeFromString(String message) {
        for (ErrorCode e : ErrorCode.values()) {
            if (e.getMessage().equals(message)) {
                return e;
            }
        }
        return null;
    }
}
