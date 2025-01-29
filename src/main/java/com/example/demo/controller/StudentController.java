package com.example.demo.controller;

import com.example.demo.dto.request.StudentCreateRequest;
import com.example.demo.dto.request.StudentUpdateRequest;
import com.example.demo.dto.request.UserUpdateRequest;
import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.StudentResponse;
import com.example.demo.dto.response.SubjectResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.mapper.SubjectMapper;
import com.example.demo.service.StudentService;
import com.example.demo.service.SubjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/students")
@Tag(name = "Student Controller")
public class StudentController {

    StudentService studentService;
    SubjectService subjectService;
    SubjectMapper subjectMapper;

    @PostMapping
    public ResponseEntity<APIResponse<StudentResponse>> createUser(@RequestBody @Valid StudentCreateRequest request) {
        APIResponse<StudentResponse> response = new APIResponse<>();
        response.setCode("201");
        response.setMessage("Student created successfully");
        response.setData(studentService.createStudent(request));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<StudentResponse>>> getAllStudents() {
        APIResponse<List<StudentResponse>> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("Get all students successfully");
        response.setData(studentService.getAllStudents());
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<APIResponse<StudentResponse>> getStudentById(@PathVariable("id") Long id) {
        APIResponse<StudentResponse> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("Get student successfully");
        response.setData(studentService.getStudentResponseById(id));
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<APIResponse<StudentResponse>> updateStudent(@PathVariable("id") Long id,
                                                                      @RequestBody @Valid StudentUpdateRequest request) {
        APIResponse<StudentResponse> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("Student updated successfully");
        response.setData(studentService.updateStudent(id, request));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        APIResponse<Void> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("Student deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/{studentId}/enroll")
    public ResponseEntity<APIResponse<Void>> enrollSubject(@PathVariable Long studentId, @RequestParam Long subjectId) {
        APIResponse<Void> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("Enroll subject successfully");

        Subject subject = subjectService.getSubjectById(subjectId);

        studentService.enrollSubject(studentId, subject);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{studentId}/subject")
    public ResponseEntity<APIResponse<Void>> deleteSubject(@PathVariable Long studentId, @RequestParam Long subjectId) {
        APIResponse<Void> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("Delete subject successfully");

        Subject subject = subjectService.getSubjectById(subjectId);

        studentService.deleteSubject(studentId, subject);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}/subjects")
    public ResponseEntity<APIResponse<List<SubjectResponse>>> getAllSubjects(@PathVariable("id") Long id) {
        APIResponse<List<SubjectResponse>> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("Get all subjects successfully");
        Student student = studentService.getStudentById(id);
        response.setData(subjectMapper.toSubjectResponseList(student.getSubjects()));
        return ResponseEntity.ok(response);
    }

}
