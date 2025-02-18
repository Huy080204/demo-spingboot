package com.example.demo.controller;

import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.lecturer.LecturerDto;
import com.example.demo.form.lecturer.CreateLecturerForm;
import com.example.demo.service.LecturerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // get paging and filtering
//    @GetMapping(path = "/list")
//    public ApiMessageDto<PageResponseDto<PeriodDto>> list(
//            @ModelAttribute PeriodCriteria periodCriteria,
//            Pageable pageable
//    ) {
//        PageResponseDto<PeriodDto> pageResponse = periodService.getPagePeriods(periodCriteria, pageable);
//
//        return ApiMessageDto.<PageResponseDto<PeriodDto>>builder()
//                .result(true)
//                .code(String.valueOf(HttpStatus.OK.value()))
//                .message("Get periods successfully")
//                .data(pageResponse)
//                .build();
//    }

    // update
//    @PutMapping(path = "/update")
//    public ResponseEntity<ApiMessageDto<PeriodDto>> update(@RequestBody @Valid UpdatePeriodForm form) {
//        PeriodDto periodDto = periodService.updatePeriod(form);
//
//        ApiMessageDto<PeriodDto> response = ApiMessageDto.<PeriodDto>builder()
//                .result(true)
//                .code(String.valueOf(HttpStatus.OK.value()))
//                .message("Update period successfully")
//                .data(periodDto)
//                .build();
//
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    // delete
//    @DeleteMapping(path = "/delete/{id}")
//    public ResponseEntity<ApiMessageDto<Void>> delete(@PathVariable Long id) {
//        periodService.deletePeriod(id);
//
//        ApiMessageDto<Void> response = ApiMessageDto.<Void>builder()
//                .result(true)
//                .code(String.valueOf(HttpStatus.OK.value()))
//                .message("Delete period successfully")
//                .build();
//
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

}
