package com.example.demo.feign;

import com.example.demo.configuration.FeignClientConfig;
import com.example.demo.constant.FeignConstant;
import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.dto.lecturerScheduler.LecturerSchedulerDto;
import com.example.demo.form.lecturerSchduler.CreateLecturerSchedulerForm;
import com.example.demo.form.lecturerSchduler.UpdateLecturerSchedulerForm;
import com.example.demo.model.criteria.LecturerSchedulerCriteria;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "lecturer-scheduler-service",
        url = FeignConstant.EXTENSION_SERVICE_URL + "/api/lecturer-scheduler",
        configuration = FeignClientConfig.class)
public interface LecturerSchedulerFeignClient {

    @PostMapping("/create")
    ApiMessageDto<LecturerSchedulerDto> create(@RequestBody @Valid CreateLecturerSchedulerForm form);

    @GetMapping(path = "/get/{id}")
    ApiMessageDto<LecturerSchedulerDto> getById(@PathVariable("id") Long id);

    @GetMapping(path = "/list")
    ApiMessageDto<PageResponseDto<LecturerSchedulerDto>> list(
            @SpringQueryMap LecturerSchedulerCriteria criteria,
            @RequestHeader(value = "CUSTOM_PAGEABLE", required = false) String pageableHeader
    );

    @PutMapping(path = "/update")
    ResponseEntity<ApiMessageDto<LecturerSchedulerDto>> update(@RequestBody @Valid UpdateLecturerSchedulerForm form);

    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity<ApiMessageDto<Void>> delete(@PathVariable Long id);
}
