package com.example.demo.dto.response.student;

import lombok.*;
        import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
public class StudentResponse {
    Long studentId;
    String username;
    String password;
    String fullName;
    LocalDate dateOfBirth;
}
