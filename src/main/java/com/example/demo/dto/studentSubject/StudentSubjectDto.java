package com.example.demo.dto.studentSubject;

import com.example.demo.dto.student.StudentDto;
import com.example.demo.dto.subject.SubjectDto;
import com.example.demo.enumeration.StudentSubjectStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
public class StudentSubjectDto {
    Long studentSubjectId;
    StudentDto studentResponse;
    SubjectDto subjectResponse;
    StudentSubjectStatus status;
    LocalDate dateRegister;
}
