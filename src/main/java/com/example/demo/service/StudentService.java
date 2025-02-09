package com.example.demo.service;

import com.example.demo.dto.student.StudentDto;
import com.example.demo.form.student.UpdateStudentForm;
import com.example.demo.model.criteria.StudentCriteria;
import com.example.demo.form.student.CreateStudentForm;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.model.Student;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    StudentDto createStudent(CreateStudentForm request);

    List<StudentDto> getAllStudents();

    StudentDto getStudentResponseById(Long id);

    Student getStudentById(Long id);

    StudentDto updateStudent(UpdateStudentForm request);

    void deleteStudent(Long id);

    PageResponseDto<StudentDto> getPageStudents(StudentCriteria studentCriteria, Pageable pageable);

}
