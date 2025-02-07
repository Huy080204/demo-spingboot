package com.example.demo.controller;

import com.example.demo.dto.student.StudentDto;
import com.example.demo.form.student.UpdateStudentForm;
import com.example.demo.model.criteria.StudentCriteria;
import com.example.demo.model.criteria.SubjectCriteria;
import com.example.demo.form.student.CreateStudentForm;
import com.example.demo.dto.APIResponseDto;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.dto.subject.SubjectDto;
import com.example.demo.model.entity.Student;
import com.example.demo.mapper.SubjectMapper;
import com.example.demo.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/student")
@Tag(name = "Student Controller")
public class StudentController {

    StudentService studentService;

    // create
    @PostMapping(path = "/create")
    public ResponseEntity<APIResponseDto<StudentDto>> create(@RequestBody @Valid CreateStudentForm request) {
        StudentDto studentResponse = studentService.createStudent(request);

        APIResponseDto<StudentDto> response = APIResponseDto.<StudentDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Student created successfully")
                .data(studentResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // get all
    @GetMapping(path = "/list-all")
    public ResponseEntity<APIResponseDto<List<StudentDto>>> getAllStudents() {
        List<StudentDto> studentResponses = studentService.getAllStudents();

        APIResponseDto<List<StudentDto>> response = APIResponseDto.<List<StudentDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get all students successfully")
                .data(studentResponses)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // get by id
    @GetMapping(path = "/get-by-id/{id}")
    public ResponseEntity<APIResponseDto<StudentDto>> getStudentById(@PathVariable("id") Long id) {
        StudentDto studentResponse = studentService.getStudentResponseById(id);

        APIResponseDto<StudentDto> response = APIResponseDto.<StudentDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get student successfully")
                .data(studentResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // update
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<APIResponseDto<StudentDto>> updateStudent(@PathVariable("id") Long id,
                                                                    @RequestBody @Valid UpdateStudentForm request) {
        StudentDto updatedStudent = studentService.updateStudent(id, request);

        APIResponseDto<StudentDto> response = APIResponseDto.<StudentDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Student updated successfully")
                .data(updatedStudent)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // delete by id
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<APIResponseDto<Void>> deleteUser(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);

        APIResponseDto<Void> response = APIResponseDto.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Student deleted successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // paging and filtering
    @GetMapping(path = "/list")
    public APIResponseDto<PageResponseDto<StudentDto>> list(
            @ModelAttribute StudentCriteria studentCriteria,
            Pageable pageable
    ) {
        PageResponseDto<StudentDto> pageResponse = studentService.getPageStudents(studentCriteria, pageable);

        return APIResponseDto.<PageResponseDto<StudentDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get students successfully")
                .data(pageResponse)
                .build();
    }

}
