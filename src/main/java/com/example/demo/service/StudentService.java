package com.example.demo.service;

import com.example.demo.dto.request.student.StudentCreateRequest;
import com.example.demo.dto.request.student.StudentUpdateRequest;
import com.example.demo.dto.response.PageResponse;
import com.example.demo.dto.response.student.StudentResponse;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;

import java.time.LocalDate;
import java.util.List;

public interface StudentService {

    StudentResponse createStudent(StudentCreateRequest request);

    List<StudentResponse> getAllStudents();

    StudentResponse getStudentResponseById(Long id);

    Student getStudentById(Long id);

    StudentResponse updateStudent(Long id, StudentUpdateRequest request);

    void deleteStudent(Long id);

    PageResponse<StudentResponse> getPageStudents(int page, int size, String fullName, LocalDate birthDate);

}
