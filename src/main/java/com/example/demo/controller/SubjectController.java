package com.example.demo.controller;

import com.example.demo.dto.request.StudentCreateRequest;
import com.example.demo.dto.request.StudentUpdateRequest;
import com.example.demo.dto.request.SubjectCreateRequest;
import com.example.demo.dto.request.SubjectUpdateRequest;
import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.StudentResponse;
import com.example.demo.dto.response.SubjectResponse;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.service.StudentService;
import com.example.demo.service.SubjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
    StudentMapper studentMapper;

    @PostMapping
    public ResponseEntity<APIResponse<SubjectResponse>> createSubject(@RequestBody @Valid SubjectCreateRequest request) {
        APIResponse<SubjectResponse> response = new APIResponse<>();
        response.setCode("201");
        response.setMessage("Subject created successfully");
        response.setData(subjectService.createSubject(request));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<SubjectResponse>>> getAllStudents() {
        APIResponse<List<SubjectResponse>> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("Get all subjects successfully");
        response.setData(subjectService.getAllSubjects());
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<APIResponse<SubjectResponse>> getStudentById(@PathVariable("id") Long id) {
        APIResponse<SubjectResponse> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("Get subject successfully");
        response.setData(subjectService.getSubjectResponseById(id));
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<APIResponse<SubjectResponse>> updateStudent(@PathVariable("id") Long id,
                                                                      @RequestBody @Valid SubjectUpdateRequest request) {
        APIResponse<SubjectResponse> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("Subject updated successfully");
        response.setData(subjectService.updateSubject(id, request));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable("id") Long id) {
        subjectService.deleteSubject(id);
        APIResponse<Void> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("Subject deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}/students")
    public ResponseEntity<APIResponse<List<StudentResponse>>> getAllStudentsBySubjectId(@PathVariable("id") Long id) {
        APIResponse<List<StudentResponse>> response = new APIResponse<>();
        response.setCode("200");
        response.setMessage("Get all students successfully");
        response.setData(studentMapper.toStudentResponseList(subjectService.getSubjectById(id).getStudents()));
        return ResponseEntity.ok(response);
    }

}
