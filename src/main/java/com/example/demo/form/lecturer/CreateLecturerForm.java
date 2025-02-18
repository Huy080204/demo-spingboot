package com.example.demo.form.lecturer;

import com.example.demo.enumeration.Gender;
import com.example.demo.validation.ValidGender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CreateLecturerForm {

    @NotBlank(message = "Username cannot be null or empty")
    String username;

    @NotBlank(message = "Password cannot be null or empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
            message = "Password must contain at least one uppercase letter, one special character"
    )
    String password;

    @NotBlank(message = "Full name cannot be null or empty")
    String fullName;

    @NotBlank(message = "Career cannot be null or empty")
    String career;

    @ValidGender(allowNull = true)
    Gender gender;
}
