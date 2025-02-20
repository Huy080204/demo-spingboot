package com.example.demo.service;

import com.example.demo.dto.user.UserDto;
import com.example.demo.form.user.CreateUserForm;
import com.example.demo.form.user.UpdateUserForm;
import com.example.demo.model.User;
import com.example.demo.security.CustomUserDetails;

import java.util.List;

public interface UserService {
    UserDto createUser(CreateUserForm request);

    List<UserDto> getAllUsers();

    UserDto getUserResponseById(String id);

    User getUserById(String id);

    UserDto updateUser(String id, UpdateUserForm request);

    void deleteUser(String id);

    UserDto getProfile(CustomUserDetails userDetails);
}
