package com.example.demo.dto.request.studentSubject;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class StudentSubjectCreateRequest {

    @NotNull(message = "Student cannot be null")
    Long studentId;

    @NotNull(message = "Subject cannot be null")
    Long subjectId;

}
