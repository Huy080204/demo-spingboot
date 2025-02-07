package com.example.demo.form.subject;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CreateSubjectForm {
    @NotEmpty(message = "Subject name cannot be null or empty")
    String subjectName;

    @NotEmpty(message = "Subject code cannot be null or empty")
    String subjectCode;
}
