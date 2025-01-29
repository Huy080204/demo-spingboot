package com.example.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
public class StudentResponse {
    Long id;
    String username;
    String password;
    String fullName;
    LocalDate birthDate;
}
