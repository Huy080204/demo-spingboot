package com.example.demo.form.studentSubject;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CreateStudentSubjectForm {

    @NotNull(message = "Student cannot be null")
    Long studentId;

    @NotNull(message = "Subject cannot be null")
    Long subjectId;

}
