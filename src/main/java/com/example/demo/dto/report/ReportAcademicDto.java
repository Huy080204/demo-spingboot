package com.example.demo.dto.report;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportAcademicDto {
    int totalCourses;
    int totalStudents;
    int maleStudents;
    int femaleStudents;
}
