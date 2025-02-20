package com.example.demo.form.subject;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Schema(name = "CreateSubjectForm", description = "Request body for creating a new subject")
public class CreateSubjectForm {
    @NotBlank(message = "Subject name cannot be null or empty")
    @Schema(description = "Name for the subject", example = "Web Programing")
    String subjectName;

    @NotBlank(message = "Subject code cannot be null or empty")
    @Schema(description = "Code for the subject", example = "WEP")
    String subjectCode;
}
