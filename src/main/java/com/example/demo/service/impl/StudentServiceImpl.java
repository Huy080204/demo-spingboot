package com.example.demo.service.impl;

import com.example.demo.criteria.StudentCriteria;
import com.example.demo.criteria.SubjectCriteria;
import com.example.demo.dto.request.student.StudentCreateRequest;
import com.example.demo.dto.request.student.StudentUpdateRequest;
import com.example.demo.dto.response.PageResponse;
import com.example.demo.dto.response.student.StudentResponse;
import com.example.demo.dto.response.subject.SubjectResponse;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentSubject;
import com.example.demo.entity.Subject;
import com.example.demo.exception.AppException;
import com.example.demo.enumeration.ErrorCode;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.SubjectMapper;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.service.StudentService;
import jakarta.persistence.criteria.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;
    SubjectRepository subjectRepository;
    StudentMapper studentMapper;
    SubjectMapper subjectMapper;

    @Override
    public StudentResponse createStudent(StudentCreateRequest request) {
        if (studentRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXITED);
        }

        Student student = studentMapper.toStudent(request);

        return studentMapper.toStudentResponse(studentRepository.save(student));
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentMapper.toStudentResponseList(studentRepository.findAll());
    }

    @Override
    public StudentResponse getStudentResponseById(Long id) {
        return studentMapper.toStudentResponse(studentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentUpdateRequest request) {
        Student student = getStudentById(id);

        studentMapper.updateStudent(student, request);

        return studentMapper.toStudentResponse(studentRepository.save(student));
    }

    @Override
    public void deleteStudent(Long id) {
        getStudentById(id);
        studentRepository.deleteById(id);
    }

    @Override
    public PageResponse<StudentResponse> getPageStudents(StudentCriteria studentCriteria, Pageable pageable) {
        Page<Student> pageData = studentRepository.findAll(studentCriteria.getCriteria(), pageable);

        return PageResponse.<StudentResponse>builder()
                .currentPage(pageable.getPageNumber())
                .totalPages(pageData.getTotalPages())
                .pageSize(pageable.getPageSize())
                .totalElements(pageData.getTotalElements())
                .data(studentMapper.toStudentResponseList(pageData.getContent()))
                .build();
    }

    @Override
    public PageResponse<SubjectResponse> getPageSubjectsByStudent(Long studentId, SubjectCriteria subjectCriteria, Pageable pageable) {
        Specification<Subject> subjectSpec = subjectCriteria.getCriteria();

        Specification<Subject> studentSpec = (root, query, cb) -> {
            Join<Subject, StudentSubject> studentSubjectJoin = root.join("studentSubjects");
            Join<StudentSubject, Student> studentJoin = studentSubjectJoin.join("student");

            return cb.equal(studentJoin.get("id"), studentId);
        };

        Specification<Subject> finalSpec = subjectSpec.and(studentSpec);

        Page<Subject> pageData = subjectRepository.findAll(finalSpec, pageable);

        return PageResponse.<SubjectResponse>builder()
                .currentPage(pageable.getPageNumber())
                .totalPages(pageData.getTotalPages())
                .pageSize(pageable.getPageSize())
                .totalElements(pageData.getTotalElements())
                .data(subjectMapper.toSubjectResponseList(pageData.getContent()))
                .build();
    }
}
