package com.example.demo.controller;

import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.PageResponseDto;
import com.example.demo.dto.period.PeriodDto;
import com.example.demo.feign.PeriodFeignClient;
import com.example.demo.form.period.CreatePeriodForm;
import com.example.demo.form.period.UpdatePeriodForm;
import com.example.demo.model.criteria.PeriodCriteria;
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
@RequestMapping(path = "/api/period")
@Tag(name = "Period Controller")
public class PeriodController {

    PeriodFeignClient periodFeignClient;

    // create
    @PostMapping(path = "/create")
    public ResponseEntity<ApiMessageDto<PeriodDto>> create(@RequestBody @Valid CreatePeriodForm form) {
        return periodFeignClient.create(form);
    }

    // get by id
    @GetMapping(path = "/get/{id}")
    public ApiMessageDto<PeriodDto> getById(@PathVariable Long id) {
        return periodFeignClient.getById(id);
    }

    // get paging and filtering
    @GetMapping("/list")
    public ApiMessageDto<PageResponseDto<PeriodDto>> getExternalPeriods(
            PeriodCriteria periodCriteria,
            Pageable pageable
    ) {
        return periodFeignClient.getListPeriods(periodCriteria, pageable);
    }

    // update
    @PutMapping(path = "/update")
    public ResponseEntity<ApiMessageDto<PeriodDto>> update(@RequestBody @Valid UpdatePeriodForm form) {
        return periodFeignClient.update(form);
    }

    // delete
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<ApiMessageDto<Void>> delete(@PathVariable Long id) {
        return periodFeignClient.delete(id);
    }

}
