package com.example.demo.controller;

import com.example.demo.dto.student.StudentDto;
import com.example.demo.form.student.UpdateStudentForm;
import com.example.demo.model.criteria.StudentCriteria;
import com.example.demo.form.student.CreateStudentForm;
import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.repository.projection.StudentProjection;
import com.example.demo.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/student")
@Tag(name = "Student Controller")
public class StudentController {

    StudentService studentService;

    // create
    @PostMapping(path = "/create")
    @PreAuthorize("hasAuthority('STU_CRE')")
    public ResponseEntity<ApiMessageDto<StudentDto>> create(@RequestBody @Valid CreateStudentForm request) {
        StudentDto studentResponse = studentService.createStudent(request);

        ApiMessageDto<StudentDto> response = ApiMessageDto.<StudentDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Student created successfully")
                .data(studentResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // get all
    @GetMapping(path = "/list-all")
    @PreAuthorize("hasAuthority('STU_GET')")
    public ResponseEntity<ApiMessageDto<List<StudentProjection>>> getAllStudents() {
        List<StudentProjection> studentResponses = studentService.getAllStudents();

        ApiMessageDto<List<StudentProjection>> response = ApiMessageDto.<List<StudentProjection>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get all students successfully")
                .data(studentResponses)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // get by id
    @GetMapping(path = "/get/{id}")
    @PreAuthorize("hasAuthority('STU_GET')")
    public ResponseEntity<ApiMessageDto<StudentDto>> getStudentById(@PathVariable("id") Long id) {
        StudentDto studentResponse = studentService.getStudentResponseById(id);

        ApiMessageDto<StudentDto> response = ApiMessageDto.<StudentDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get student successfully")
                .data(studentResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // update
    @PutMapping(path = "/update")
    @PreAuthorize("hasAuthority('STU_UPD')")
    public ResponseEntity<ApiMessageDto<StudentDto>> updateStudent(@RequestBody @Valid UpdateStudentForm request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Current user authorities: " + authentication.getAuthorities());
        StudentDto updatedStudent = studentService.updateStudent(request);

        ApiMessageDto<StudentDto> response = ApiMessageDto.<StudentDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Student updated successfully")
                .data(updatedStudent)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // delete by id
    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('STU_DEL')")
    public ResponseEntity<ApiMessageDto<Void>> deleteUser(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);

        ApiMessageDto<Void> response = ApiMessageDto.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Student deleted successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // paging and filtering
    @GetMapping(path = "/list")
    @PreAuthorize("hasAuthority('STU_GET')")
    public ApiMessageDto<PageResponseDto<StudentDto>> list(
            @ModelAttribute StudentCriteria studentCriteria,
            Pageable pageable
    ) {
        PageResponseDto<StudentDto> pageResponse = studentService.getPageStudents(studentCriteria, pageable);

        return ApiMessageDto.<PageResponseDto<StudentDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get students successfully")
                .data(pageResponse)
                .build();
    }

}
