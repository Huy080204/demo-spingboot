package com.example.demo.controller;

import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.dto.lecturerScheduler.LecturerSchedulerDto;
import com.example.demo.feign.LecturerSchedulerFeignClient;
import com.example.demo.form.lecturerSchduler.CreateLecturerSchedulerForm;
import com.example.demo.form.lecturerSchduler.UpdateLecturerSchedulerForm;
import com.example.demo.model.criteria.LecturerSchedulerCriteria;
import com.example.demo.service.LecturerService;
import com.example.demo.service.SubjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/lecturer-scheduler")
@Tag(name = "Lecturer Scheduler Controller")
public class LecturerSchedulerController {

    LecturerSchedulerFeignClient lecturerSchedulerFeignClient;
    LecturerService lecturerService;
    SubjectService subjectService;

    // create
    @PostMapping("/create")
    public ApiMessageDto<LecturerSchedulerDto> getExternalPeriods(@RequestBody @Valid CreateLecturerSchedulerForm form) {
        // check lecturer exists
        lecturerService.getLecturer(form.getLecturerId());
        // check subject exists
        subjectService.getSubjectById(form.getSubjectId());

        return lecturerSchedulerFeignClient.create(form);
    }

    // get paging and filtering
    @GetMapping(path = "/get/{id}")
    public ApiMessageDto<LecturerSchedulerDto> getById(@PathVariable("id") Long id) {
        return lecturerSchedulerFeignClient.getById(id);
    }

    // get paging and filtering
    @GetMapping(path = "/list")
    public ApiMessageDto<PageResponseDto<LecturerSchedulerDto>> list(
            @ModelAttribute LecturerSchedulerCriteria lecturerSchedulerCriteria,
            Pageable pageable
    ) {
        return lecturerSchedulerFeignClient.list(lecturerSchedulerCriteria, pageable);
    }

    // update
    @PutMapping(path = "/update")
    public ResponseEntity<ApiMessageDto<LecturerSchedulerDto>> update(@RequestBody @Valid UpdateLecturerSchedulerForm form) {
        // check lecturer exists
        lecturerService.getLecturer(form.getLecturerId());
        // check subject exists
        subjectService.getSubjectById(form.getSubjectId());

        return lecturerSchedulerFeignClient.update(form);
    }

    // delete
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<ApiMessageDto<Void>> update(@PathVariable Long id) {
        return lecturerSchedulerFeignClient.delete(id);
    }

}
