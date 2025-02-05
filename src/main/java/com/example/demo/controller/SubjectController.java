package com.example.demo.controller;

import com.example.demo.dto.request.subject.SubjectCreateRequest;
import com.example.demo.dto.request.subject.SubjectUpdateRequest;
import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.PageResponse;
import com.example.demo.dto.response.subject.SubjectResponse;
import com.example.demo.service.SubjectService;
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
@RequestMapping(path = "/api/subjects")
@Tag(name = "Subject Controller")
public class SubjectController {

    SubjectService subjectService;

    @PostMapping
    public ResponseEntity<APIResponse<SubjectResponse>> createSubject(@RequestBody @Valid SubjectCreateRequest request) {
        SubjectResponse subjectResponse = subjectService.createSubject(request);

        APIResponse<SubjectResponse> response = APIResponse.<SubjectResponse>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Subject created successfully")
                .data(subjectResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<SubjectResponse>>> getAllStudents() {
        List<SubjectResponse> subjects = subjectService.getAllSubjects();

        APIResponse<List<SubjectResponse>> response = APIResponse.<List<SubjectResponse>>builder()
                .result(true)
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
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get subject successfully")
                .data(subjectResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<APIResponse<SubjectResponse>> updateStudent(@PathVariable("id") Long id,
                                                                      @RequestBody @Valid SubjectUpdateRequest request) {
        SubjectResponse subjectResponse = subjectService.updateSubject(id, request);

        APIResponse<SubjectResponse> response = APIResponse.<SubjectResponse>builder()
                .result(true)
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
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Subject deleted successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/list")
    public APIResponse<PageResponse<SubjectResponse>> list(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "subjectName", required = false) String subjectName
    ) {
        PageResponse<SubjectResponse> pageResponse = subjectService.getPageSubjects(page, size, subjectName);

        return APIResponse.<PageResponse<SubjectResponse>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get students successfully")
                .data(pageResponse)
                .build();
    }

}
