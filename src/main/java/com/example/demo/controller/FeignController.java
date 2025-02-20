package com.example.demo.controller;

import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.dto.lecturerScheduler.LecturerSchedulerDto;
import com.example.demo.dto.period.PeriodDto;
import com.example.demo.feign.LecturerSchedulerFeignClient;
import com.example.demo.feign.PeriodFeignClient;
import com.example.demo.form.lecturerSchduler.CreateLecturerSchedulerForm;
import com.example.demo.model.criteria.PeriodCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/feign")
@Tag(name = "Feign Controller")
public class FeignController {
    PeriodFeignClient periodFeignClient;
    LecturerSchedulerFeignClient lecturerSchedulerFeignClient;

    @GetMapping("/external-periods")
    public ApiMessageDto<PageResponseDto<PeriodDto>> getExternalPeriods(
            PeriodCriteria periodCriteria,
            Pageable pageable
    ) {
        return periodFeignClient.getListPeriods(periodCriteria, pageable.getPageNumber(), pageable.getPageSize());
    }

    @PostMapping("/enroll")
    public ApiMessageDto<LecturerSchedulerDto> getExternalPeriods(@RequestBody @Valid CreateLecturerSchedulerForm form) {
        return lecturerSchedulerFeignClient.create(form);
    }
}
