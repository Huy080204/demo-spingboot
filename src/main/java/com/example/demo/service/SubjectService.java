package com.example.demo.service;

import com.example.demo.dto.subject.SubjectDto;
import com.example.demo.form.subject.CreateSubjectForm;
import com.example.demo.form.subject.UpdateSubjectForm;
import com.example.demo.model.criteria.SubjectCriteria;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.model.Subject;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubjectService {
    SubjectDto createSubject(CreateSubjectForm request);

    List<SubjectDto> getAllSubjects();

    SubjectDto getSubjectResponseById(Long id);

    Subject getSubjectById(Long id);

    SubjectDto updateSubject(UpdateSubjectForm request);

    void deleteSubject(Long id);

    PageResponseDto<SubjectDto> getPageSubjects(SubjectCriteria subjectCriteria, Pageable pageable);
}