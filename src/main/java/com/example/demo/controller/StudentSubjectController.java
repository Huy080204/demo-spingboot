package com.example.demo.controller;

import com.example.demo.dto.request.studentSubject.StudentSubjectCreateRequest;
import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.student.StudentResponse;
import com.example.demo.dto.response.studentSubject.StudentSubjectResponse;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentSubject;
import com.example.demo.entity.Subject;
import com.example.demo.service.StudentService;
import com.example.demo.service.StudentSubjectService;
import com.example.demo.service.SubjectService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/student-subjects")

public class StudentSubjectController {

    StudentService studentService;
    SubjectService subjectService;
    StudentSubjectService studentSubjectService;

    @PostMapping
    public ResponseEntity<APIResponse<StudentSubjectResponse>> createStudentSubject(@Valid @RequestBody StudentSubjectCreateRequest request) {
        Student student = studentService.getStudentById(request.getStudentId());
        Subject subject = subjectService.getSubjectById(request.getSubjectId());

        StudentSubjectResponse studentSubject = studentSubjectService.createStudentSubject(student, subject);

        APIResponse<StudentSubjectResponse> response = APIResponse.<StudentSubjectResponse>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Create student subject successfully")
                .data(studentSubject)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<APIResponse<StudentSubjectResponse>> getStudentSubjectById(@PathVariable("id") Long id) {
        StudentSubjectResponse studentSubject = studentSubjectService.getStudentSubjectResponseById(id);

        APIResponse<StudentSubjectResponse> response = APIResponse.<StudentSubjectResponse>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get successfully")
                .data(studentSubject)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<APIResponse<StudentSubjectResponse>> updateStudentSubject(@PathVariable("id") Long id) {
        StudentSubjectResponse studentSubject = studentSubjectService.updateStudentSubject(id);

        APIResponse<StudentSubjectResponse> response = APIResponse.<StudentSubjectResponse>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get successfully")
                .data(studentSubject)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<APIResponse<Void>> deleteStudentSubjectById(@PathVariable("id") Long id) {
        StudentSubjectResponse studentSubject = studentSubjectService.updateStudentSubject(id);

        APIResponse<Void> response = APIResponse.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Delete successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
