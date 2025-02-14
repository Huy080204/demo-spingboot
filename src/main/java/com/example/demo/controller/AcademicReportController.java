package com.example.demo.controller;

import com.example.demo.dto.APIMessageDto;
import com.example.demo.dto.report.ReportAcademicDto;
import com.example.demo.service.StudentSubjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/report")
public class AcademicReportController {

    StudentSubjectService studentSubjectService;

    @GetMapping("/academic")
    public APIMessageDto<ReportAcademicDto> getAcademicReport() {
        ReportAcademicDto report = studentSubjectService.getAcademicReport();

        return APIMessageDto.<ReportAcademicDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get academic report successfully")
                .data(report)
                .build();
    }


}
