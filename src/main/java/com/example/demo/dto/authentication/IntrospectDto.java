package com.example.demo.dto.authentication;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
public class IntrospectDto {
    boolean valid;
}
