package com.example.demo.service.impl;

import com.example.demo.dto.request.student.StudentCreateRequest;
import com.example.demo.dto.request.student.StudentUpdateRequest;
import com.example.demo.dto.response.student.StudentResponse;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

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
    public void enrollSubject(Long studentId, Subject subject) {
        Student student = getStudentById(studentId);
        student.getSubjects().add(subject);
        studentRepository.save(student);
    }

    @Override
    public void deleteSubject(Long studentId, Subject subject) {
        Student student = getStudentById(studentId);
        if (!student.getSubjects().contains(subject)) {
            throw new AppException(ErrorCode.SUBJECT_NOT_FOUND);
        }
        student.getSubjects().remove(subject);
        studentRepository.save(student);
    }

}
