package com.example.demo.controller;

import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.lecturerScheduler.LecturerSchedulerDto;
import com.example.demo.feign.LecturerSchedulerFeignClient;
import com.example.demo.form.lecturerSchduler.CreateLecturerSchedulerForm;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/feign")
@Tag(name = "Feign Controller")
public class FeignController {
    LecturerSchedulerFeignClient lecturerSchedulerFeignClient;

    @PostMapping("/enroll")
    public ApiMessageDto<LecturerSchedulerDto> getExternalPeriods(@RequestBody @Valid CreateLecturerSchedulerForm form) {
        return lecturerSchedulerFeignClient.create(form);
    }
}
