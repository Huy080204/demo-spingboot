package com.example.demo.form.subject;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class UpdateSubjectForm {

    @NotNull(message = "Subject id cannot be null")
    Long id;

    @NotEmpty(message = "Subject name cannot be null or empty")
    String subjectName;
}
