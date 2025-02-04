package com.example.demo.service;

import com.example.demo.dto.response.studentSubject.StudentSubjectResponse;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentSubject;
import com.example.demo.entity.Subject;

public interface StudentSubjectService {

    StudentSubjectResponse createStudentSubject(Student student, Subject subject);

    StudentSubject getStudentSubjectById(Long id);

    StudentSubjectResponse getStudentSubjectResponseById(Long id);

    StudentSubjectResponse updateStudentSubject(Long id);

    void deleteStudentSubjectById(Long id);
}
