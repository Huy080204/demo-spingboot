package com.example.demo.controller;

import com.example.demo.dto.request.student.StudentCreateRequest;
import com.example.demo.dto.request.student.StudentUpdateRequest;
import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.student.StudentResponse;
import com.example.demo.dto.response.subject.SubjectResponse;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.mapper.SubjectMapper;
import com.example.demo.service.StudentService;
import com.example.demo.service.impl.SubjectServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/students")
@Tag(name = "Student Controller")
public class StudentController {

    StudentService studentService;
    SubjectServiceImpl subjectService;
    SubjectMapper subjectMapper;

    @PostMapping
    public ResponseEntity<APIResponse<StudentResponse>> createUser(@RequestBody @Valid StudentCreateRequest request) {
        StudentResponse studentResponse = studentService.createStudent(request);

        APIResponse<StudentResponse> response = APIResponse.<StudentResponse>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Student created successfully")
                .data(studentResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<StudentResponse>>> getAllStudents() {
        List<StudentResponse> studentResponses = studentService.getAllStudents();

        APIResponse<List<StudentResponse>> response = APIResponse.<List<StudentResponse>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get all students successfully")
                .data(studentResponses)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<APIResponse<StudentResponse>> getStudentById(@PathVariable("id") Long id) {
        StudentResponse studentResponse = studentService.getStudentResponseById(id);

        APIResponse<StudentResponse> response = APIResponse.<StudentResponse>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get student successfully")
                .data(studentResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<APIResponse<StudentResponse>> updateStudent(@PathVariable("id") Long id,
                                                                      @RequestBody @Valid StudentUpdateRequest request) {
        StudentResponse updatedStudent = studentService.updateStudent(id, request);

        APIResponse<StudentResponse> response = APIResponse.<StudentResponse>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Student updated successfully")
                .data(updatedStudent)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);

        APIResponse<Void> response = APIResponse.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Student deleted successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/{id}/subjects")
    public ResponseEntity<APIResponse<List<SubjectResponse>>> getAllSubjects(@PathVariable("id") Long id) {
        Student student = studentService.getStudentById(id);
        List<SubjectResponse> subjectResponses = student.getStudentSubjects().stream()
                .map(studentSubject -> subjectMapper.toSubjectResponse(studentSubject.getSubject()))
                .collect(Collectors.toList());

        APIResponse<List<SubjectResponse>> response = APIResponse.<List<SubjectResponse>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get all subjects successfully")
                .data(subjectResponses)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
