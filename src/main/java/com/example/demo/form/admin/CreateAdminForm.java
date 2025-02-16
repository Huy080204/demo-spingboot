package com.example.demo.form.admin;

import com.example.demo.enumeration.Gender;
import com.example.demo.validation.ValidGender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CreateAdminForm {
    @NotEmpty(message = "Username cannot be null or empty")
    String username;

    @NotEmpty(message = "Password cannot be null or empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
            message = "Password must contain at least one uppercase letter, one special character"
    )
    String password;

    @NotEmpty(message = "Full name cannot be null or empty")
    String fullName;

    @NotNull(message = "Level cannot be null or empty")
    Integer level;

    @NotNull(message = "Is super admin cannot be null or empty")
    Boolean superAdmin;

    @ValidGender(allowNull = true)
    Gender gender;
}
