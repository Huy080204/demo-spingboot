package com.example.demo.controller;

import com.example.demo.dto.request.PostCreateRequest;
import com.example.demo.dto.request.UserCreateRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users")
@Tag(name = "User Controller")
public class UserController {

    UserService userService;
    PostService postService;

    @PostMapping
    public ResponseEntity<APIResponse<UserResponse>> createUser(@RequestBody @Valid UserCreateRequest request) {
        APIResponse<UserResponse> response = new APIResponse<>();
        response.setCode("201");
        response.setMessage("User created successfully");
        response.setData(userService.createUser(request));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<UserResponse>>> getAllUsers() {
        APIResponse<List<UserResponse>> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("Get all users successfully");
        response.setData(userService.getAllUsers());
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<APIResponse<UserResponse>> getUserById(@PathVariable("id") String id) {
        APIResponse<UserResponse> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("Get user successfully");
        response.setData(userService.getUserResponseById(id));
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<APIResponse<UserResponse>> updateUser(@PathVariable("id") String id, @RequestBody @Valid UserUpdateRequest request) {
        APIResponse<UserResponse> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("User updated successfully");
        response.setData(userService.updateUser(id, request));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        APIResponse<Void> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("User deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/{userId}/posts")
    public ResponseEntity<APIResponse<Post>> createPost(@PathVariable("userId") String userId, @RequestBody @Valid PostCreateRequest request) {
        User user = userService.getUserById(userId);
        APIResponse<Post> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("Post created successfully");
        response.setData(postService.createPost(user, request));
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{userId}/posts")
    public ResponseEntity<APIResponse<List<Post>>> getAllPostByUser(@PathVariable("userId") String userId) {
        User user = userService.getUserById(userId);
        APIResponse<List<Post>> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("Post created successfully");
        response.setData(user.getPosts());
        return ResponseEntity.ok(response);
    }

}
