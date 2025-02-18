package com.example.demo.controller;

import com.example.demo.model.criteria.SubjectCriteria;
import com.example.demo.form.subject.CreateSubjectForm;
import com.example.demo.form.subject.UpdateSubjectForm;
import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.dto.subject.SubjectDto;
import com.example.demo.repository.StudentSubjectRepository;
import com.example.demo.service.SubjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/subject")
@Tag(name = "Subject Controller")
public class SubjectController {

    SubjectService subjectService;
    StudentSubjectRepository studentSubjectRepository;

    // create
    @PostMapping(path = "/create")
    @PreAuthorize("hasAuthority('SUB_CRE')")
    public ResponseEntity<ApiMessageDto<SubjectDto>> create(@RequestBody @Valid CreateSubjectForm request) {
        SubjectDto subjectResponse = subjectService.createSubject(request);

        ApiMessageDto<SubjectDto> response = ApiMessageDto.<SubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Subject created successfully")
                .data(subjectResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // get all
    @GetMapping(path = "/list-all")
    @PreAuthorize("hasAuthority('SUB_GET')")
    public ResponseEntity<ApiMessageDto<List<SubjectDto>>> listAll() {
        List<SubjectDto> subjects = subjectService.getAllSubjects();

        ApiMessageDto<List<SubjectDto>> response = ApiMessageDto.<List<SubjectDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get all subjects successfully")
                .data(subjects)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // get by id
    @GetMapping(path = "/get/{id}")
    @PreAuthorize("hasAuthority('SUB_GET')")
    public ResponseEntity<ApiMessageDto<SubjectDto>> getById(@PathVariable("id") Long id) {
        SubjectDto subjectResponse = subjectService.getSubjectResponseById(id);

        ApiMessageDto<SubjectDto> response = ApiMessageDto.<SubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get subject successfully")
                .data(subjectResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // update
    @PutMapping(path = "/update")
    @PreAuthorize("hasAuthority('SUB_UPD')")
    public ResponseEntity<ApiMessageDto<SubjectDto>> update(@RequestBody @Valid UpdateSubjectForm request) {
        SubjectDto subjectResponse = subjectService.updateSubject(request);

        ApiMessageDto<SubjectDto> response = ApiMessageDto.<SubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Subject updated successfully")
                .data(subjectResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // delete by id
    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('SUB_DEL')")
    public ResponseEntity<ApiMessageDto<Void>> deleteById(@PathVariable("id") Long id) {
        subjectService.deleteSubject(id);

        ApiMessageDto<Void> response = ApiMessageDto.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Subject deleted successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // paging and filtering
    @GetMapping(path = "/list")
    @PreAuthorize("hasAuthority('SUB_GET')")
    public ApiMessageDto<PageResponseDto<SubjectDto>> list(
            @ModelAttribute SubjectCriteria subjectCriteria,
            Pageable pageable
    ) {
        PageResponseDto<SubjectDto> pageResponse = subjectService.getPageSubjects(subjectCriteria, pageable);

        return ApiMessageDto.<PageResponseDto<SubjectDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get subject successfully")
                .data(pageResponse)
                .build();
    }

    // check all student done
    @GetMapping("/{subjectId}/all-done")
    @PreAuthorize("hasAuthority('SUB_GET')")
    public ResponseEntity<String> checkIfAllStudentsDone(@PathVariable Long subjectId) {
        List<Long> completedSubjects = studentSubjectRepository.findSubjectsWithAllStudentsDone();

        if (completedSubjects.contains(subjectId)) {
            return ResponseEntity.ok("All students in subject " + subjectId + " have completed.");
        } else {
            return ResponseEntity.ok("Some students in subject " + subjectId + " have not completed.");
        }
    }

}
