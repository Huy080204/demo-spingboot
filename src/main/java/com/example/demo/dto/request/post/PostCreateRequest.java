package com.example.demo.dto.request.post;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class PostCreateRequest {

    @NotNull(message = "Description cannot be null")
    @NotEmpty(message = "Description cannot be empty")
    String description;

}
