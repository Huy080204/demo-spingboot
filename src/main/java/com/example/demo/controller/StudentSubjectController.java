package com.example.demo.controller;

import com.example.demo.dto.studentSubject.StudentSubjectDto;
import com.example.demo.form.studentSubject.CreateStudentSubjectForm;
import com.example.demo.dto.APIResponseDto;
import com.example.demo.model.entity.Student;
import com.example.demo.model.entity.Subject;
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
    public ResponseEntity<APIResponseDto<StudentSubjectDto>> createStudentSubject(@Valid @RequestBody CreateStudentSubjectForm request) {
        Student student = studentService.getStudentById(request.getStudentId());
        Subject subject = subjectService.getSubjectById(request.getSubjectId());

        StudentSubjectDto studentSubject = studentSubjectService.createStudentSubject(student, subject);

        APIResponseDto<StudentSubjectDto> response = APIResponseDto.<StudentSubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Create student subject successfully")
                .data(studentSubject)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // get by id
    @GetMapping(path = "/get-by-id/{id}")
    public ResponseEntity<APIResponseDto<StudentSubjectDto>> getById(@PathVariable("id") Long id) {
        StudentSubjectDto studentSubject = studentSubjectService.getStudentSubjectResponseById(id);

        APIResponseDto<StudentSubjectDto> response = APIResponseDto.<StudentSubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get successfully")
                .data(studentSubject)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // update
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<APIResponseDto<StudentSubjectDto>> update(@PathVariable("id") Long id) {
        StudentSubjectDto studentSubject = studentSubjectService.updateStudentSubject(id);

        APIResponseDto<StudentSubjectDto> response = APIResponseDto.<StudentSubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get successfully")
                .data(studentSubject)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // delete by id
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<APIResponseDto<Void>> delete(@PathVariable("id") Long id) {
        StudentSubjectDto studentSubject = studentSubjectService.updateStudentSubject(id);

        APIResponseDto<Void> response = APIResponseDto.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Delete successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
