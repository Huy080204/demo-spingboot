package com.example.demo.form.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class UpdateUserForm {

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters")
    String password;

    @NotNull(message = "First name cannot be null")
    @NotEmpty(message = "First name cannot be empty")
    String firstName;

    @NotNull(message = "Last name cannot be null")
    @NotEmpty(message = "Last name cannot be empty")
    String lastName;

    @NotNull(message = "Date of birth name cannot be null")
    @Past(message = "Date of birth must be in the past")
    LocalDate dob;

}
