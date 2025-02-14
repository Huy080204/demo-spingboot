package com.example.demo.dto.user;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
public class UserDto {
    String username;
    String fullName;
    String avatar;
}
