package com.example.demo.dto.request.subject;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class SubjectUpdateRequest {
    @NotNull(message = "Subject name cannot be null")
    @NotEmpty(message = "Subject name cannot be empty")
    String subjectName;
}
