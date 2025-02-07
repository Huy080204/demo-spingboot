package com.example.demo.dto.user;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
public class UserDto {
    String id;
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
}
