package com.example.demo.feign;

import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.dto.period.PeriodDto;
import com.example.demo.model.criteria.PeriodCriteria;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "period-service", url = "http://localhost:8383/api/period")
public interface PeriodFeignClient {
    @PostMapping("/list")
    ApiMessageDto<PageResponseDto<PeriodDto>> getListPeriods(
            PeriodCriteria periodCriteria,
            Pageable pageable
    );
}
