package com.example.demo.controller;

import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.dto.period.PeriodDto;
import com.example.demo.feign.PeriodFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/feign")
@Tag(name = "Feign Controller")
public class FeignController {
    PeriodFeignClient periodFeignClient;

    @GetMapping("/external-periods")
    public ApiMessageDto<PageResponseDto<PeriodDto>> getExternalPeriods(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            Pageable pageable
    ) {
        return periodFeignClient.getListPeriods(
                name,
                description,
                pageable.getPageNumber(),
                pageable.getPageSize()
        );
    }
}
