package com.example.demo.mapper;

import com.example.demo.dto.user.UserDto;
import com.example.demo.form.user.CreateUserForm;
import com.example.demo.form.user.UpdateUserForm;
import com.example.demo.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(CreateUserForm request);
    void updateUser(@MappingTarget User user, UpdateUserForm request);
    UserDto toUserResponse(User user);
    List<UserDto> toUserResponseList(List<User> users);
}
