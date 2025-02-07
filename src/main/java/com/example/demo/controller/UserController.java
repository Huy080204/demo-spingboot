package com.example.demo.controller;

import com.example.demo.dto.user.UserDto;
import com.example.demo.form.post.CreatePostForm;
import com.example.demo.form.user.CreateUserForm;
import com.example.demo.form.user.UpdateUserForm;
import com.example.demo.dto.APIResponseDto;
import com.example.demo.model.entity.Post;
import com.example.demo.model.entity.User;
import com.example.demo.service.PostService;
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
    public ResponseEntity<APIResponseDto<UserDto>> createUser(@RequestBody @Valid CreateUserForm request) {
        APIResponseDto<UserDto> response = APIResponseDto.<UserDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED))
                .message("User created successfully")
                .data(userService.createUser(request))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<APIResponseDto<List<UserDto>>> getAllUsers() {
        APIResponseDto<List<UserDto>> response = APIResponseDto.<List<UserDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get all users successfully")
                .data(userService.getAllUsers())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<APIResponseDto<UserDto>> getUserById(@PathVariable("id") String id) {
        APIResponseDto<UserDto> response = APIResponseDto.<UserDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get user successfully")
                .data(userService.getUserResponseById(id))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<APIResponseDto<UserDto>> updateUser(@PathVariable("id") String id, @RequestBody @Valid UpdateUserForm request) {
        APIResponseDto<UserDto> response = APIResponseDto.<UserDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("User updated successfully")
                .data(userService.updateUser(id, request))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<APIResponseDto<Void>> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        APIResponseDto<Void> response = APIResponseDto.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("User deleted successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = "/{userId}/posts")
    public ResponseEntity<APIResponseDto<Post>> createPost(@PathVariable("userId") String userId, @RequestBody @Valid CreatePostForm request) {
        User user = userService.getUserById(userId);
        Post post = postService.createPost(user, request);

        APIResponseDto<Post> response = APIResponseDto.<Post>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Post created successfully")
                .data(post)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/{userId}/posts")
    public ResponseEntity<APIResponseDto<List<Post>>> getAllPostByUser(@PathVariable("userId") String userId) {
        User user = userService.getUserById(userId);

        APIResponseDto<List<Post>> response = APIResponseDto.<List<Post>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Posts retrieved successfully")
                .data(user.getPosts())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
