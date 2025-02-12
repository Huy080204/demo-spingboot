package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class APIMessageDto<T> {
    boolean result = true;
    String code = null;
    String message = null;
    T data = null;
}
