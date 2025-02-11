package com.example.demo.controller;

import com.example.demo.model.criteria.SubjectCriteria;
import com.example.demo.form.subject.CreateSubjectForm;
import com.example.demo.form.subject.UpdateSubjectForm;
import com.example.demo.dto.APIMessageDto;
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
    public ResponseEntity<APIMessageDto<SubjectDto>> create(@RequestBody @Valid CreateSubjectForm request) {
        SubjectDto subjectResponse = subjectService.createSubject(request);

        APIMessageDto<SubjectDto> response = APIMessageDto.<SubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Subject created successfully")
                .data(subjectResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // get all
    @GetMapping(path = "/list-all")
    public ResponseEntity<APIMessageDto<List<SubjectDto>>> listAll() {
        List<SubjectDto> subjects = subjectService.getAllSubjects();

        APIMessageDto<List<SubjectDto>> response = APIMessageDto.<List<SubjectDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get all subjects successfully")
                .data(subjects)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // get by id
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<APIMessageDto<SubjectDto>> getById(@PathVariable("id") Long id) {
        SubjectDto subjectResponse = subjectService.getSubjectResponseById(id);

        APIMessageDto<SubjectDto> response = APIMessageDto.<SubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get subject successfully")
                .data(subjectResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // update
    @PutMapping(path = "/update")
    public ResponseEntity<APIMessageDto<SubjectDto>> update(@RequestBody @Valid UpdateSubjectForm request) {
        SubjectDto subjectResponse = subjectService.updateSubject(request);

        APIMessageDto<SubjectDto> response = APIMessageDto.<SubjectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Subject updated successfully")
                .data(subjectResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // delete by id
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<APIMessageDto<Void>> deleteById(@PathVariable("id") Long id) {
        subjectService.deleteSubject(id);

        APIMessageDto<Void> response = APIMessageDto.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Subject deleted successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // paging and filtering
    @GetMapping(path = "/list")
    public APIMessageDto<PageResponseDto<SubjectDto>> list(
            @ModelAttribute SubjectCriteria subjectCriteria,
            Pageable pageable
    ) {
        PageResponseDto<SubjectDto> pageResponse = subjectService.getPageSubjects(subjectCriteria, pageable);

        return APIMessageDto.<PageResponseDto<SubjectDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get subject successfully")
                .data(pageResponse)
                .build();
    }

    // check all student done
    @GetMapping("/{subjectId}/all-done")
    public ResponseEntity<String> checkIfAllStudentsDone(@PathVariable Long subjectId) {
        List<Long> completedSubjects = studentSubjectRepository.findSubjectsWithAllStudentsDone();

        if (completedSubjects.contains(subjectId)) {
            return ResponseEntity.ok("All students in subject " + subjectId + " have completed.");
        } else {
            return ResponseEntity.ok("Some students in subject " + subjectId + " have not completed.");
        }
    }

}
