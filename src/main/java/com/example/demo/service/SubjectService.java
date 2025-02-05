package com.example.demo.service;

import com.example.demo.dto.request.subject.SubjectCreateRequest;
import com.example.demo.dto.request.subject.SubjectUpdateRequest;
import com.example.demo.dto.response.PageResponse;
import com.example.demo.dto.response.student.StudentResponse;
import com.example.demo.dto.response.subject.SubjectResponse;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;

import java.time.LocalDate;
import java.util.List;

public interface SubjectService {
    SubjectResponse createSubject(SubjectCreateRequest request);

    List<SubjectResponse> getAllSubjects();

    SubjectResponse getSubjectResponseById(Long id);

    Subject getSubjectById(Long id);

    SubjectResponse updateSubject(Long id, SubjectUpdateRequest request);

    void deleteSubject(Long id);

    PageResponse<SubjectResponse> getPageSubjects(int page, int size, String subjectName);
}