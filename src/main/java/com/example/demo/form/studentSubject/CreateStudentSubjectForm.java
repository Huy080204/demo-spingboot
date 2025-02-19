package com.example.demo.form.studentSubject;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Schema(name = "CreateStudentSubjectForm", description = "Request body for creating a new student subject")
public class CreateStudentSubjectForm {

    @NotNull(message = "Student cannot be null")
    @Schema(description = "Id for the student", example = "1")
    Long studentId;

    @NotNull(message = "Subject cannot be null")
    @Schema(description = "Id for the subject", example = "1")
    Long subjectId;

}
