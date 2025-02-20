package com.example.demo.form.subject;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Schema(name = "UpdateSubjectForm", description = "Request body for updating a new subject")
public class UpdateSubjectForm {

    @NotNull(message = "Subject id cannot be null")
    @Schema(description = "Id for the subject", example = "1")
    Long id;

    @NotBlank(message = "Subject name cannot be null or empty")
    @Schema(description = "Name for the subject", example = "Web Programing")
    String subjectName;
}
