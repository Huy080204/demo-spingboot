package com.example.demo.dto.request.student;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class StudentUpdateRequest {
    @NotNull(message = "Username cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters")
    String password;

    @NotNull(message = "Full name cannot be null")
    @NotEmpty(message = "Full name cannot be empty")
    String fullName;

    @NotNull(message = "Date of birth name cannot be null")
    @Past(message = "Date of birth must be in the past")
    LocalDate dateOfBirth;
}
