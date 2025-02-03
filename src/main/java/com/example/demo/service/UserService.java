package com.example.demo.service;

import com.example.demo.dto.request.user.UserCreateRequest;
import com.example.demo.dto.request.user.UserUpdateRequest;
import com.example.demo.dto.response.user.UserResponse;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserCreateRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserResponseById(String id);

    User getUserById(String id);

    UserResponse updateUser(String id, UserUpdateRequest request);

    void deleteUser(String id);
}
