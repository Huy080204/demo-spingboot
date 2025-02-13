package com.example.demo.dto.authentication;

import com.example.demo.dto.user.UserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class AuthenticationDto {
    String token;
    UserDto user;
    List<String> authorities;
}
