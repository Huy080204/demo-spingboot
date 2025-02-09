package com.example.demo.form.studentSubject;

import com.example.demo.enumeration.StudentSubjectStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class UpdateStudentSubjectForm {
    Long studentId;
    Long subjectId;
    StudentSubjectStatus status;
    Boolean done;
}
