package com.example.demo.form.student;

import com.example.demo.enumeration.Gender;
import com.example.demo.validation.ValidGender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Schema(name = "CreateStudentForm", description = "Request body for creating a new student")
public class CreateStudentForm {

    @NotBlank(message = "Username cannot be null or empty")
    @Schema(description = "Unique username for the student", example = "NguyenVanA")
    String username;

    @NotBlank(message = "Password cannot be null or empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
            message = "Password must contain at least one uppercase letter, one special character"
    )
    @Schema(description = "Password with at least 6 characters, including one uppercase letter and one special character", example = "1234A@")
    String password;

    @NotBlank(message = "Full name cannot be null or empty")
    @Schema(description = "Full name of the student", example = "Nguyen Van A")
    String fullName;

    @NotNull(message = "Date of birth name cannot be null")
    @Past(message = "Date of birth must be in the past")
    @Schema(description = "Date of birth (must be in the past)", example = "2004-02-08")
    LocalDate dateOfBirth;

    @ValidGender(allowNull = true)
    @Schema(description = "Gender of the student (MALE, FEMALE or OTHER)", example = "MALE")
    Gender gender;
}
