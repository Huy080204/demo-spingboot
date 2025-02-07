package com.example.demo.controller;

import com.example.demo.model.criteria.SubjectCriteria;
import com.example.demo.form.subject.CreateSubjectForm;
import com.example.demo.form.subject.UpdateSubjectForm;
import com.example.demo.dto.APIResponseDto;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.dto.subject.SubjectDto;
import com.example.demo.service.SubjectService;
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

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/subject")
@Tag(name = "Subject Controller")
public class SubjectController {

    SubjectService subjectService;

    // create
    @PostMapping(path = "/create")
    public ResponseEntity<APIResponseDto<SubjectDto>> create(@RequestBody @Valid CreateSubjectForm request) {
        SubjectDto subjectResponse = subjectService.createSubject(request);

        APIResponseDto<SubjectDto> response = APIResponseDto.<SubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Subject created successfully")
                .data(subjectResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // get all
    @GetMapping(path = "/list-all")
    public ResponseEntity<APIResponseDto<List<SubjectDto>>> listAll() {
        List<SubjectDto> subjects = subjectService.getAllSubjects();

        APIResponseDto<List<SubjectDto>> response = APIResponseDto.<List<SubjectDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get all subjects successfully")
                .data(subjects)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // get by id
    @GetMapping(path = "/get-by-id/{id}")
    public ResponseEntity<APIResponseDto<SubjectDto>> getById(@PathVariable("id") Long id) {
        SubjectDto subjectResponse = subjectService.getSubjectResponseById(id);

        APIResponseDto<SubjectDto> response = APIResponseDto.<SubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get subject successfully")
                .data(subjectResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // update
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<APIResponseDto<SubjectDto>> update(@PathVariable("id") Long id,
                                                             @RequestBody @Valid UpdateSubjectForm request) {
        SubjectDto subjectResponse = subjectService.updateSubject(id, request);

        APIResponseDto<SubjectDto> response = APIResponseDto.<SubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Subject updated successfully")
                .data(subjectResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // delete by id
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<APIResponseDto<Void>> deleteById(@PathVariable("id") Long id) {
        subjectService.deleteSubject(id);

        APIResponseDto<Void> response = APIResponseDto.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Subject deleted successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // paging and filtering
    @GetMapping(path = "/list")
    public APIResponseDto<PageResponseDto<SubjectDto>> list(
            @ModelAttribute SubjectCriteria subjectCriteria,
            Pageable pageable
    ) {
        PageResponseDto<SubjectDto> pageResponse = subjectService.getPageSubjects(subjectCriteria, pageable);

        return APIResponseDto.<PageResponseDto<SubjectDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get subject successfully")
                .data(pageResponse)
                .build();
    }

}
