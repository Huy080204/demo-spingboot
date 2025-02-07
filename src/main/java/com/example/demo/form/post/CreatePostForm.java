package com.example.demo.form.post;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CreatePostForm {

    @NotNull(message = "Description cannot be null")
    @NotEmpty(message = "Description cannot be empty")
    String description;

}
