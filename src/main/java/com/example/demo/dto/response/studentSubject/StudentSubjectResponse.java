package com.example.demo.dto.response.studentSubject;

import com.example.demo.dto.response.student.StudentResponse;
import com.example.demo.dto.response.subject.SubjectResponse;
import com.example.demo.enumeration.StudentSubjectStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
public class StudentSubjectResponse {
    Long studentSubjectId;
    StudentResponse studentResponse;
    SubjectResponse subjectResponse;
    StudentSubjectStatus status;
    LocalDate dateRegister;
}
