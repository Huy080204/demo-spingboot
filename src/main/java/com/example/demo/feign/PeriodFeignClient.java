package com.example.demo.feign;

import com.example.demo.configuration.FeignClientConfig;
import com.example.demo.constant.FeignConstant;
import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.dto.period.PeriodDto;
import com.example.demo.form.period.CreatePeriodForm;
import com.example.demo.form.period.UpdatePeriodForm;
import com.example.demo.model.criteria.PeriodCriteria;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "period-service",
        url = FeignConstant.EXTENSION_SERVICE_URL + "/api/period",
        configuration = FeignClientConfig.class
)
public interface PeriodFeignClient {

    @PostMapping("/create")
    ResponseEntity<ApiMessageDto<PeriodDto>> create(@RequestBody @Valid CreatePeriodForm form);

    @GetMapping("/get/{id}")
    ApiMessageDto<PeriodDto> getById(@PathVariable Long id);

    @GetMapping("/list")
    ApiMessageDto<PageResponseDto<PeriodDto>> getListPeriods(
            @SpringQueryMap PeriodCriteria periodCriteria,
            Pageable pageable
    );

    @PutMapping("/update")
    ResponseEntity<ApiMessageDto<PeriodDto>> update(@RequestBody @Valid UpdatePeriodForm form);

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<ApiMessageDto<Void>> delete(@PathVariable Long id);
}
