package com.example.demo.form.student;

import com.example.demo.enumeration.Gender;
import com.example.demo.validation.ValidGender;
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
public class UpdateStudentForm {
    @NotEmpty(message = "Username cannot be null or empty")
    @Size(min = 8, message = "Password must be at least 8 characters")
    String password;

    @NotEmpty(message = "Full name cannot be null or empty")
    String fullName;

    @NotNull(message = "Date of birth name cannot be null")
    @Past(message = "Date of birth must be in the past")
    LocalDate dateOfBirth;

    @NotNull(message = "Gender cannot be null")
    @ValidGender(allowNull = false)
    Gender gender;
}
