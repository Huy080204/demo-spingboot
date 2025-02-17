package com.example.demo.controller;

import com.example.demo.dto.user.UserDto;
import com.example.demo.form.user.CreateUserForm;
import com.example.demo.form.user.UpdateUserForm;
import com.example.demo.dto.APIMessageDto;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/user")
@Tag(name = "User Controller")
public class UserController {

    UserService userService;

    @PostMapping(path = "/create")
    public ResponseEntity<APIMessageDto<UserDto>> createUser(@RequestBody @Valid CreateUserForm request) {
        APIMessageDto<UserDto> response = APIMessageDto.<UserDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED))
                .message("User created successfully")
                .data(userService.createUser(request))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<APIMessageDto<List<UserDto>>> getAllUsers() {
        APIMessageDto<List<UserDto>> response = APIMessageDto.<List<UserDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get all users successfully")
                .data(userService.getAllUsers())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<APIMessageDto<UserDto>> getUserById(@PathVariable("id") String id) {
        APIMessageDto<UserDto> response = APIMessageDto.<UserDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get user successfully")
                .data(userService.getUserResponseById(id))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<APIMessageDto<UserDto>> updateUser(@PathVariable("id") String id, @RequestBody @Valid UpdateUserForm request) {
        APIMessageDto<UserDto> response = APIMessageDto.<UserDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("User updated successfully")
                .data(userService.updateUser(id, request))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<APIMessageDto<Void>> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        APIMessageDto<Void> response = APIMessageDto.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("User deleted successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<APIMessageDto<UserDto>> getProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(APIMessageDto.<UserDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("User profile retrieved successfully")
                .data(userService.getProfile(userDetails))
                .build());
    }

}
