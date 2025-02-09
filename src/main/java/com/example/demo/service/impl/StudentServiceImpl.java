package com.example.demo.service.impl;

import com.example.demo.dto.student.StudentDto;
import com.example.demo.model.criteria.StudentCriteria;
import com.example.demo.form.student.CreateStudentForm;
import com.example.demo.form.student.UpdateStudentForm;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.model.Student;
import com.example.demo.exception.AppException;
import com.example.demo.enumeration.ErrorCode;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
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
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;
    StudentMapper studentMapper;

    // create
    @Override
    public StudentDto createStudent(CreateStudentForm request) {
        if (studentRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXITED);
        }

        Student student = studentMapper.toStudent(request);

        return studentMapper.toStudentResponse(studentRepository.save(student));
    }

    // get all
    @Override
    public List<StudentDto> getAllStudents() {
        return studentMapper.toStudentResponseList(studentRepository.findAll());
    }

    // get student dto by id
    @Override
    public StudentDto getStudentResponseById(Long id) {
        return studentMapper.toStudentResponse(studentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    // get student entity by id
    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    // update
    @Override
    public StudentDto updateStudent(UpdateStudentForm request) {
        Student student = getStudentById(request.getId());

        studentMapper.updateStudent(student, request);

        return studentMapper.toStudentResponse(studentRepository.save(student));
    }

    // delete by id
    @Override
    public void deleteStudent(Long id) {
        getStudentById(id);
        studentRepository.deleteById(id);
    }

    // get list paging and filtering
    @Override
    public PageResponseDto<StudentDto> getPageStudents(StudentCriteria studentCriteria, Pageable pageable) {
        Page<Student> pageData = studentRepository.findAll(studentCriteria.getCriteria(), pageable);

        return PageResponseDto.<StudentDto>builder()
                .currentPage(pageable.getPageNumber())
                .totalPages(pageData.getTotalPages())
                .pageSize(pageable.getPageSize())
                .totalElements(pageData.getTotalElements())
                .data(studentMapper.toStudentResponseList(pageData.getContent()))
                .build();
    }

}
