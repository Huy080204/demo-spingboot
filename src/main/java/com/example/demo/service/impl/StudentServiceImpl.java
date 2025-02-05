package com.example.demo.service.impl;

import com.example.demo.dto.request.student.StudentCreateRequest;
import com.example.demo.dto.request.student.StudentUpdateRequest;
import com.example.demo.dto.response.PageResponse;
import com.example.demo.dto.response.student.StudentResponse;
import com.example.demo.entity.Student;
import com.example.demo.exception.AppException;
import com.example.demo.enumeration.ErrorCode;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;
    StudentMapper studentMapper;

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
    public PageResponse<StudentResponse> getPageStudents(int page, int size, String fullName, LocalDate birthDate) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Specification<Student> spec = (Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (fullName != null && !fullName.trim().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("fullName")), "%" + fullName.toLowerCase() + "%"));
            }

            if (birthDate != null) {
                predicates.add(cb.equal(root.get("birthDate"), birthDate));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<Student> pageData = studentRepository.findAll(spec, pageable);

        return PageResponse.<StudentResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(size)
                .totalElements(pageData.getTotalElements())
                .data(studentMapper.toStudentResponseList(pageData.getContent()))
                .build();
    }
}
