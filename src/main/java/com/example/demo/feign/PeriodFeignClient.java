package com.example.demo.feign;

import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.dto.period.PeriodDto;
import com.example.demo.model.criteria.PeriodCriteria;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "period-service", url = "http://localhost:8383/api/period")
public interface PeriodFeignClient {
    @GetMapping("/list")
    ApiMessageDto<PageResponseDto<PeriodDto>> getListPeriods(
            @SpringQueryMap PeriodCriteria periodCriteria,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    );
}
