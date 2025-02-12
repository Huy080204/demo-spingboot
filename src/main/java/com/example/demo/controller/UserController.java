package com.example.demo.controller;

import com.example.demo.dto.user.UserDto;
import com.example.demo.form.post.CreatePostForm;
import com.example.demo.form.user.CreateUserForm;
import com.example.demo.form.user.UpdateUserForm;
import com.example.demo.dto.APIMessageDto;
import com.example.demo.model.Post;
import com.example.demo.model.User;
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
    public ResponseEntity<APIMessageDto<UserDto>> createUser(@RequestBody @Valid CreateUserForm request) {
        APIMessageDto<UserDto> response = APIMessageDto.<UserDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED))
                .message("User created successfully")
                .data(userService.createUser(request))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<APIMessageDto<List<UserDto>>> getAllUsers() {
        APIMessageDto<List<UserDto>> response = APIMessageDto.<List<UserDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get all users successfully")
                .data(userService.getAllUsers())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<APIMessageDto<UserDto>> getUserById(@PathVariable("id") String id) {
        APIMessageDto<UserDto> response = APIMessageDto.<UserDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get user successfully")
                .data(userService.getUserResponseById(id))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<APIMessageDto<UserDto>> updateUser(@PathVariable("id") String id, @RequestBody @Valid UpdateUserForm request) {
        APIMessageDto<UserDto> response = APIMessageDto.<UserDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("User updated successfully")
                .data(userService.updateUser(id, request))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<APIMessageDto<Void>> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        APIMessageDto<Void> response = APIMessageDto.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("User deleted successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = "/{userId}/posts")
    public ResponseEntity<APIMessageDto<Post>> createPost(@PathVariable("userId") String userId, @RequestBody @Valid CreatePostForm request) {
        User user = userService.getUserById(userId);
        Post post = postService.createPost(user, request);

        APIMessageDto<Post> response = APIMessageDto.<Post>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Post created successfully")
                .data(post)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/{userId}/posts")
    public ResponseEntity<APIMessageDto<List<Post>>> getAllPostByUser(@PathVariable("userId") String userId) {
        User user = userService.getUserById(userId);

        APIMessageDto<List<Post>> response = APIMessageDto.<List<Post>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Posts retrieved successfully")
                .data(user.getPosts())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
