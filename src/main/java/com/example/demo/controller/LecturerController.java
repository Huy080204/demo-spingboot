package com.example.demo.controller;

import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.dto.lecturer.LecturerDto;
import com.example.demo.form.lecturer.CreateLecturerForm;
import com.example.demo.form.lecturer.UpdateLecturerForm;
import com.example.demo.model.criteria.LecturerCriteria;
import com.example.demo.service.LecturerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/lecturer")
@Tag(name = "Lecturer Controller")
public class LecturerController {

    LecturerService lecturerService;

    // create
    @PostMapping(path = "/create")
    public ResponseEntity<ApiMessageDto<LecturerDto>> create(@RequestBody @Valid CreateLecturerForm form) {
        LecturerDto lecturerDto = lecturerService.createLecturer(form);

        ApiMessageDto<LecturerDto> response = ApiMessageDto.<LecturerDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Create lecturer successfully")
                .data(lecturerDto)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // get by id
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<ApiMessageDto<LecturerDto>> getById(@PathVariable Long id) {
        LecturerDto lecturerDto = lecturerService.getLecturer(id);

        ApiMessageDto<LecturerDto> response = ApiMessageDto.<LecturerDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get lecturer successfully")
                .data(lecturerDto)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // get paging and filtering
    @GetMapping(path = "/list")
    public ApiMessageDto<PageResponseDto<LecturerDto>> list(
            @ModelAttribute LecturerCriteria lecturerCriteria,
            Pageable pageable
    ) {
        PageResponseDto<LecturerDto> pageResponse = lecturerService.getPageLecturers(lecturerCriteria, pageable);

        return ApiMessageDto.<PageResponseDto<LecturerDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get lecturers successfully")
                .data(pageResponse)
                .build();
    }

    // update
    @PutMapping(path = "/update")
    public ResponseEntity<ApiMessageDto<LecturerDto>> update(@RequestBody @Valid UpdateLecturerForm form) {
        LecturerDto lecturerDto = lecturerService.updateLecturer(form);

        ApiMessageDto<LecturerDto> response = ApiMessageDto.<LecturerDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Update lecturer successfully")
                .data(lecturerDto)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // delete
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<ApiMessageDto<Void>> delete(@PathVariable Long id) {
        lecturerService.deleteLecturer(id);

        ApiMessageDto<Void> response = ApiMessageDto.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Delete lecturer successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
