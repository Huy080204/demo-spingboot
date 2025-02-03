package com.example.demo.service;

import com.example.demo.dto.request.subject.SubjectCreateRequest;
import com.example.demo.dto.request.subject.SubjectUpdateRequest;
import com.example.demo.dto.response.subject.SubjectResponse;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;

import java.util.List;

public interface SubjectService {
    SubjectResponse createSubject(SubjectCreateRequest request);

    List<SubjectResponse> getAllSubjects();

    SubjectResponse getSubjectResponseById(Long id);

    Subject getSubjectById(Long id);

    SubjectResponse updateSubject(Long id, SubjectUpdateRequest request);

    void deleteSubject(Long id);

    void addStudentToSubject(Subject subject, Student student);

    void deleteSubjectForStudent(Long id, Student student);
}