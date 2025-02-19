package com.example.demo.form.studentSubject;

import com.example.demo.enumeration.StudentSubjectStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Schema(name = "UpdateStudentSubjectForm", description = "Request body for updating a new student subject")
public class UpdateStudentSubjectForm {
    @NotNull(message = "Student cannot be null")
    @Schema(description = "Id for the student", example = "1")
    Long studentId;

    @NotNull(message = "Subject cannot be null")
    @Schema(description = "Id for the subject", example = "1")
    Long subjectId;

    @Schema(description = "Status of the student's subject (optional, can be PENDING or ACTIVE)",
            example = "ACTIVE", nullable = true)
    StudentSubjectStatus status;

    @Schema(description = "Indicates whether the student has completed the subject (optional)",
            example = "true", nullable = true)
    Boolean done;
}
