package com.example.demo.form.authentication;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class AuthenticationForm {
    String username;
    String password;
}
