package com.example.demo.service;

import com.example.demo.dto.user.UserDto;
import com.example.demo.form.user.CreateUserForm;
import com.example.demo.form.user.UpdateUserForm;
import com.example.demo.model.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(CreateUserForm request);

    List<UserDto> getAllUsers();

    UserDto getUserResponseById(String id);

    User getUserById(String id);

    UserDto updateUser(String id, UpdateUserForm request);

    void deleteUser(String id);
}
