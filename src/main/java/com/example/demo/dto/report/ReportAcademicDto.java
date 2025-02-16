package com.example.demo.dto.report;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportAcademicDto {
    Long totalCourses;
    Long totalStudents;
    Long maleStudents;
    Long femaleStudents;
}
