package com.example.demo.controller;

import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.studentSubject.StudentSubjectDto;
import com.example.demo.form.studentSubject.CreateStudentSubjectForm;
import com.example.demo.form.studentSubject.UpdateStudentSubjectForm;
import com.example.demo.model.Student;
import com.example.demo.model.Subject;
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
@RequestMapping("/api/student-subject")

public class StudentSubjectController {

    StudentService studentService;
    SubjectService subjectService;
    StudentSubjectService studentSubjectService;

    // create
    @PostMapping(path = "/create")
    public ResponseEntity<ApiMessageDto<StudentSubjectDto>> createStudentSubject(@Valid @RequestBody CreateStudentSubjectForm request) {
        Student student = studentService.getStudentById(request.getStudentId());
        Subject subject = subjectService.getSubjectById(request.getSubjectId());

        StudentSubjectDto studentSubject = studentSubjectService.createStudentSubject(student, subject);

        ApiMessageDto<StudentSubjectDto> response = ApiMessageDto.<StudentSubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Create student subject successfully")
                .data(studentSubject)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // get by id
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<ApiMessageDto<StudentSubjectDto>> getById(@PathVariable("id") Long id) {
        StudentSubjectDto studentSubject = studentSubjectService.getStudentSubjectResponseById(id);

        ApiMessageDto<StudentSubjectDto> response = ApiMessageDto.<StudentSubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get successfully")
                .data(studentSubject)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // update
    @PutMapping(path = "/update")
    public ResponseEntity<ApiMessageDto<StudentSubjectDto>> update(@RequestBody UpdateStudentSubjectForm form) {

        Student student = studentService.getStudentById(form.getStudentId());
        Subject subject = subjectService.getSubjectById(form.getSubjectId());

        StudentSubjectDto studentSubject = studentSubjectService.updateStudentSubject(student, subject, form.getStatus(), form.getDone());

        ApiMessageDto<StudentSubjectDto> response = ApiMessageDto.<StudentSubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get successfully")
                .data(studentSubject)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // delete by id
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<ApiMessageDto<Void>> delete(@PathVariable("id") Long id) {
        studentSubjectService.deleteStudentSubjectById(id);

        ApiMessageDto<Void> response = ApiMessageDto.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Delete successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
