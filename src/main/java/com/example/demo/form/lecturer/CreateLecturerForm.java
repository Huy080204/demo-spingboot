package com.example.demo.form.lecturer;

import com.example.demo.enumeration.Gender;
import com.example.demo.validation.ValidGender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Schema(name = "CreateLecturerForm", description = "Request body for creating a new lecturer")
public class CreateLecturerForm {

    @NotBlank(message = "Username cannot be null or empty")
    @Schema(description = "Unique username for the lecturer", example = "NguyenVanA")
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
    @Schema(description = "Full name of the lecturer", example = "Nguyen Van A")
    String fullName;

    @NotBlank(message = "Career cannot be null or empty")
    @Schema(description = "Career of the lecturer", example = "Web Programing")
    String career;

    @ValidGender(allowNull = true)
    @Schema(description = "Gender of the student (MALE, FEMALE or OTHER)", example = "MALE")
    Gender gender;
}
