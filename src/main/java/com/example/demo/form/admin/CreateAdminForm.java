package com.example.demo.form.admin;

import com.example.demo.enumeration.Gender;
import com.example.demo.validation.ValidGender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Schema(name = "CreateAdminForm", description = "Request body for creating a new admin")
public class CreateAdminForm {
    @NotEmpty(message = "Username cannot be null or empty")
    @Schema(description = "Unique username for the admin", example = "admin")
    String username;

    @NotEmpty(message = "Password cannot be null or empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
            message = "Password must contain at least one uppercase letter, one special character"
    )
    @Schema(description = "Password with at least 6 characters, including one uppercase letter and one special character", example = "1234A@")
    String password;

    @NotEmpty(message = "Full name cannot be null or empty")
    @Schema(description = "Full name of the admin", example = "Admin")
    String fullName;

    @NotNull(message = "Level cannot be null or empty")
    @Schema(description = "Level of the admin", example = "1")
    Integer level;

    @ValidGender(allowNull = true)
    @Schema(description = "Gender of the student (MALE, FEMALE or OTHER)", example = "MALE")
    Gender gender;
}
