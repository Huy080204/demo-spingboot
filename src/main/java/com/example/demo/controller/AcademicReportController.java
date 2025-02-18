package com.example.demo.controller;

import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.report.ReportAcademicDto;
import com.example.demo.service.StudentSubjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/report")
public class AcademicReportController {

    StudentSubjectService studentSubjectService;

    @GetMapping("/academic")
    public ApiMessageDto<ReportAcademicDto> getAcademicReport() {
        ReportAcademicDto report = studentSubjectService.getAcademicReport();

        return ApiMessageDto.<ReportAcademicDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get academic report successfully")
                .data(report)
                .build();
    }


}
