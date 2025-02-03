package com.example.demo.controller;

import com.example.demo.dto.request.subject.SubjectCreateRequest;
import com.example.demo.dto.request.subject.SubjectUpdateRequest;
import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.student.StudentResponse;
import com.example.demo.dto.response.subject.SubjectResponse;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.service.StudentService;
import com.example.demo.service.SubjectService;
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

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/subjects")
@Tag(name = "Subject Controller")
public class SubjectController {

    SubjectService subjectService;
    StudentService studentService;
    StudentMapper studentMapper;

    @PostMapping
    public ResponseEntity<APIResponse<SubjectResponse>> createSubject(@RequestBody @Valid SubjectCreateRequest request) {
        SubjectResponse subjectResponse = subjectService.createSubject(request);

        APIResponse<SubjectResponse> response = APIResponse.<SubjectResponse>builder()
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Subject created successfully")
                .data(subjectResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(path = "/{subjectId}")
    public ResponseEntity<APIResponse<Void>> addStudentToSubject(@PathVariable Long subjectId, @RequestParam Long studentId) {
        Subject subject = subjectService.getSubjectById(subjectId);
        Student student = studentService.getStudentById(studentId);

        subjectService.addStudentToSubject(subject, student);

        APIResponse<Void> response = APIResponse.<Void>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Add student to subject successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<SubjectResponse>>> getAllStudents() {
        List<SubjectResponse> subjects = subjectService.getAllSubjects();

        APIResponse<List<SubjectResponse>> response = APIResponse.<List<SubjectResponse>>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get all subjects successfully")
                .data(subjects)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<APIResponse<SubjectResponse>> getStudentById(@PathVariable("id") Long id) {
        SubjectResponse subjectResponse = subjectService.getSubjectResponseById(id);

        APIResponse<SubjectResponse> response = APIResponse.<SubjectResponse>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get subject successfully")
                .data(subjectResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/{id}/students")
    public ResponseEntity<APIResponse<List<StudentResponse>>> getAllStudentsBySubjectId(@PathVariable("id") Long id) {
        List<StudentResponse> studentResponses = studentMapper.toStudentResponseList(subjectService.getSubjectById(id).getStudents());

        APIResponse<List<StudentResponse>> response = APIResponse.<List<StudentResponse>>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get all students successfully")
                .data(studentResponses)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<APIResponse<SubjectResponse>> updateStudent(@PathVariable("id") Long id,
                                                                      @RequestBody @Valid SubjectUpdateRequest request) {
        SubjectResponse subjectResponse = subjectService.updateSubject(id, request);

        APIResponse<SubjectResponse> response = APIResponse.<SubjectResponse>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Subject updated successfully")
                .data(subjectResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable("id") Long id) {
        subjectService.deleteSubject(id);

        APIResponse<Void> response = APIResponse.<Void>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Subject deleted successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/{subjectId}/students/{studentId}")
    public ResponseEntity<APIResponse<Void>> deleteSubjectForStudent(
            @PathVariable Long subjectId,
            @PathVariable Long studentId) {

        Student student = studentService.getStudentById(studentId);

        subjectService.deleteSubjectForStudent(subjectId, student);

        APIResponse<Void> response = APIResponse.<Void>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Delete subject successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
