package com.example.demo.dto.period;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodDto {
    Long periodId;
    String name;
    String description;
    LocalDate startDate;
    LocalDate dueDate;
    Integer state;
}
