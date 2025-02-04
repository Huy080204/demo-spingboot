package com.example.demo.controller;

import com.example.demo.dto.request.post.PostCreateRequest;
import com.example.demo.dto.request.user.UserCreateRequest;
import com.example.demo.dto.request.user.UserUpdateRequest;
import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.user.UserResponse;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.service.PostService;
import com.example.demo.service.impl.PostServiceImpl;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/users")
@Tag(name = "User Controller")
public class UserController {

    UserService userService;
    PostService postService;

    @PostMapping
    public ResponseEntity<APIResponse<UserResponse>> createUser(@RequestBody @Valid UserCreateRequest request) {
        APIResponse<UserResponse> response = APIResponse.<UserResponse>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED))
                .message("User created successfully")
                .data(userService.createUser(request))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<UserResponse>>> getAllUsers() {
        APIResponse<List<UserResponse>> response = APIResponse.<List<UserResponse>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get all users successfully")
                .data(userService.getAllUsers())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<APIResponse<UserResponse>> getUserById(@PathVariable("id") String id) {
        APIResponse<UserResponse> response = APIResponse.<UserResponse>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get user successfully")
                .data(userService.getUserResponseById(id))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<APIResponse<UserResponse>> updateUser(@PathVariable("id") String id, @RequestBody @Valid UserUpdateRequest request) {
        APIResponse<UserResponse> response = APIResponse.<UserResponse>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("User updated successfully")
                .data(userService.updateUser(id, request))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        APIResponse<Void> response = APIResponse.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("User deleted successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = "/{userId}/posts")
    public ResponseEntity<APIResponse<Post>> createPost(@PathVariable("userId") String userId, @RequestBody @Valid PostCreateRequest request) {
        User user = userService.getUserById(userId);
        Post post = postService.createPost(user, request);

        APIResponse<Post> response = APIResponse.<Post>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Post created successfully")
                .data(post)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/{userId}/posts")
    public ResponseEntity<APIResponse<List<Post>>> getAllPostByUser(@PathVariable("userId") String userId) {
        User user = userService.getUserById(userId);

        APIResponse<List<Post>> response = APIResponse.<List<Post>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Posts retrieved successfully")
                .data(user.getPosts())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
