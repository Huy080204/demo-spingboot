package com.example.demo.model.criteria;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class LecturerSchedulerCriteria {
    Long lecturerSchedulerId;
    Long lecturerId;
    Long subjectId;
    Long periodId;
}
