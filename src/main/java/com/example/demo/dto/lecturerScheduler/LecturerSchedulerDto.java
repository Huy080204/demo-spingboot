package com.example.demo.dto.lecturerScheduler;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
public class LecturerSchedulerDto {
    Long lecturerSchedulerId;
    Long lecturerId;
    Long subjectId;
    Long periodId;
}
