package com.example.demo.service;

import com.example.demo.criteria.StudentCriteria;
import com.example.demo.criteria.SubjectCriteria;
import com.example.demo.dto.request.student.StudentCreateRequest;
import com.example.demo.dto.request.student.StudentUpdateRequest;
import com.example.demo.dto.response.PageResponse;
import com.example.demo.dto.response.student.StudentResponse;
import com.example.demo.dto.response.subject.SubjectResponse;
import com.example.demo.entity.Student;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    StudentResponse createStudent(StudentCreateRequest request);

    List<StudentResponse> getAllStudents();

    StudentResponse getStudentResponseById(Long id);

    Student getStudentById(Long id);

    StudentResponse updateStudent(Long id, StudentUpdateRequest request);

    void deleteStudent(Long id);

    PageResponse<StudentResponse> getPageStudents(StudentCriteria studentCriteria, Pageable pageable);

    PageResponse<SubjectResponse> getPageSubjectsByStudent(Long studentId, SubjectCriteria subjectCriteria, Pageable pageable);

}
