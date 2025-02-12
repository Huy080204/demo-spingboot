package com.example.demo.controller;

import com.example.demo.dto.studentSubject.StudentSubjectDto;
import com.example.demo.form.studentSubject.CreateStudentSubjectForm;
import com.example.demo.dto.APIMessageDto;
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
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('S_CRE')")
    public ResponseEntity<APIMessageDto<StudentSubjectDto>> createStudentSubject(@Valid @RequestBody CreateStudentSubjectForm request) {
        Student student = studentService.getStudentById(request.getStudentId());
        Subject subject = subjectService.getSubjectById(request.getSubjectId());

        StudentSubjectDto studentSubject = studentSubjectService.createStudentSubject(student, subject);

        APIMessageDto<StudentSubjectDto> response = APIMessageDto.<StudentSubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Create student subject successfully")
                .data(studentSubject)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // get by id
    @GetMapping(path = "/get/{id}")
    @PreAuthorize("hasAuthority('S_GET')")
    public ResponseEntity<APIMessageDto<StudentSubjectDto>> getById(@PathVariable("id") Long id) {
        StudentSubjectDto studentSubject = studentSubjectService.getStudentSubjectResponseById(id);

        APIMessageDto<StudentSubjectDto> response = APIMessageDto.<StudentSubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get successfully")
                .data(studentSubject)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // update
    @PutMapping(path = "/update")
    @PreAuthorize("hasAuthority('S_UPD')")
    public ResponseEntity<APIMessageDto<StudentSubjectDto>> update(@RequestBody UpdateStudentSubjectForm form) {

        Student student = studentService.getStudentById(form.getStudentId());
        Subject subject = subjectService.getSubjectById(form.getSubjectId());

        StudentSubjectDto studentSubject = studentSubjectService.updateStudentSubject(student, subject, form.getStatus(), form.getDone());

        APIMessageDto<StudentSubjectDto> response = APIMessageDto.<StudentSubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get successfully")
                .data(studentSubject)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // delete by id
    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('S_DEL')")
    public ResponseEntity<APIMessageDto<Void>> delete(@PathVariable("id") Long id) {
        studentSubjectService.deleteStudentSubjectById(id);

        APIMessageDto<Void> response = APIMessageDto.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Delete successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
