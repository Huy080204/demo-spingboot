package com.example.demo.feign;

import com.example.demo.configuration.FeignClientConfig;
import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.lecturerScheduler.LecturerSchedulerDto;
import com.example.demo.form.lecturerSchduler.CreateLecturerSchedulerForm;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "lecturer-scheduler-service",
            url = "http://localhost:8383/api/lecturer-scheduler",
            configuration = FeignClientConfig.class)
public interface LecturerSchedulerFeignClient {
    @PostMapping("/create")
    ApiMessageDto<LecturerSchedulerDto> create(@RequestBody @Valid CreateLecturerSchedulerForm form);
}
