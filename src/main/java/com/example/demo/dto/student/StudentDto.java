package com.example.demo.dto.student;

import com.example.demo.enumeration.Gender;
import lombok.*;
        import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
public class StudentDto {
    Long studentId;
    String username;
    String fullName;
    LocalDate dateOfBirth;
    Gender gender;
}
