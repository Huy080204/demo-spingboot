package com.example.demo.service;

import com.example.demo.dto.studentSubject.StudentSubjectDto;
import com.example.demo.enumeration.StudentSubjectStatus;
import com.example.demo.model.Student;
import com.example.demo.model.StudentSubject;
import com.example.demo.model.Subject;

public interface StudentSubjectService {

    StudentSubjectDto createStudentSubject(Student student, Subject subject);

    StudentSubject getStudentSubjectById(Long id);

    StudentSubjectDto getStudentSubjectResponseById(Long id);

    StudentSubjectDto updateStudentSubject(Student student, Subject subject, StudentSubjectStatus status, Boolean done);

    void deleteStudentSubjectById(Long id);
}
