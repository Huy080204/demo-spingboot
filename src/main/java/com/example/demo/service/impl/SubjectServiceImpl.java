package com.example.demo.service.impl;

import com.example.demo.dto.request.subject.SubjectCreateRequest;
import com.example.demo.dto.request.subject.SubjectUpdateRequest;
import com.example.demo.dto.response.PageResponse;
import com.example.demo.dto.response.student.StudentResponse;
import com.example.demo.dto.response.subject.SubjectResponse;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.exception.AppException;
import com.example.demo.enumeration.ErrorCode;
import com.example.demo.mapper.SubjectMapper;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.service.SubjectService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {
    SubjectRepository subjectRepository;
    SubjectMapper subjectMapper;

    @Override
    public SubjectResponse createSubject(SubjectCreateRequest request) {
        if (subjectRepository.existsByCode(request.getSubjectCode())) {
            throw new AppException(ErrorCode.SUBJECT_CODE_EXITED);
        }

        Subject subject = subjectMapper.toSubject(request);

        return subjectMapper.toSubjectResponse(subjectRepository.save(subject));
    }

    @Override
    public List<SubjectResponse> getAllSubjects() {
        return subjectMapper.toSubjectResponseList(subjectRepository.findAll());
    }

    @Override
    public SubjectResponse getSubjectResponseById(Long id) {
        return subjectMapper.toSubjectResponse(subjectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SUBJECT_NOT_FOUND)));
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SUBJECT_NOT_FOUND));
    }

    @Override
    public SubjectResponse updateSubject(Long id, SubjectUpdateRequest request) {
        Subject subject = getSubjectById(id);

        subjectMapper.updateSubject(subject, request);

        return subjectMapper.toSubjectResponse(subjectRepository.save(subject));
    }

    @Override
    public void deleteSubject(Long id) {
        getSubjectById(id);
        subjectRepository.deleteById(id);
    }

    @Override
    public PageResponse<SubjectResponse> getPageSubjects(int page, int size, String subjectName) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Specification<Subject> spec = (Root<Subject> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (subjectName != null && !subjectName.trim().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + subjectName.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<Subject> pageData = subjectRepository.findAll(spec, pageable);

        return PageResponse.<SubjectResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(size)
                .totalElements(pageData.getTotalElements())
                .data(subjectMapper.toSubjectResponseList(pageData.getContent()))
                .build();
    }
}
