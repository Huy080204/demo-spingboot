package com.example.demo.service.impl;

import com.example.demo.dto.subject.SubjectDto;
import com.example.demo.form.subject.UpdateSubjectForm;
import com.example.demo.model.criteria.SubjectCriteria;
import com.example.demo.form.subject.CreateSubjectForm;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.model.Subject;
import com.example.demo.exception.AppException;
import com.example.demo.enumeration.ErrorCode;
import com.example.demo.mapper.SubjectMapper;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.service.SubjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {
    SubjectRepository subjectRepository;
    SubjectMapper subjectMapper;

    @Override
    public SubjectDto createSubject(CreateSubjectForm request) {
        if (subjectRepository.existsByCode(request.getSubjectCode())) {
            throw new AppException(ErrorCode.SUBJECT_CODE_EXITED);
        }

        Subject subject = subjectMapper.toSubject(request);

        return subjectMapper.toSubjectResponse(subjectRepository.save(subject));
    }

    @Override
    public List<SubjectDto> getAllSubjects() {
        return subjectMapper.toSubjectResponseList(subjectRepository.findAll());
    }

    @Override
    public SubjectDto getSubjectResponseById(Long id) {
        return subjectMapper.toSubjectResponse(subjectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SUBJECT_NOT_FOUND)));
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SUBJECT_NOT_FOUND));
    }

    @Override
    public SubjectDto updateSubject(UpdateSubjectForm request) {
        Subject subject = getSubjectById(request.getId());

        subjectMapper.updateSubject(subject, request);

        return subjectMapper.toSubjectResponse(subjectRepository.save(subject));
    }

    @Override
    public void deleteSubject(Long id) {
        getSubjectById(id);
        subjectRepository.deleteById(id);
    }

    @Override
    public PageResponseDto<SubjectDto> getPageSubjects(SubjectCriteria subjectCriteria, Pageable pageable) {
        Page<Subject> pageData = subjectRepository.findAll(subjectCriteria.getCriteria(), pageable);

        return PageResponseDto.<SubjectDto>builder()
                .currentPage(pageable.getPageNumber())
                .totalPages(pageData.getTotalPages())
                .pageSize(pageable.getPageSize())
                .totalElements(pageData.getTotalElements())
                .data(subjectMapper.toSubjectResponseList(pageData.getContent()))
                .build();
    }

}
