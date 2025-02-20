package com.example.demo.form.period;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Schema(name = "CreatePeriodForm", description = "Request body for creating a period")
public class CreatePeriodForm {
    @NotBlank(message = "Period name cannot be null or empty")
    @Schema(description = "Name of the period", example = "Spring Semester 2025")
    String name;

    @NotBlank(message = "Period description cannot be null or empty")
    @Schema(description = "Detailed description of the period", example = "This period covers all courses from January to June.")
    String description;

    @NotNull(message = "Start date cannot be null")
    @Future(message = "Start date must be in the future")
    @Schema(description = "Start date of the period (must be in the future)", example = "2025-01-15")
    LocalDate startDate;

    @NotNull(message = "Due date cannot be null")
    @Future(message = "Due date must be in the future")
    @Schema(description = "Due date of the period (must be in the future)", example = "2025-06-15")
    LocalDate dueDate;

    @NotNull
    @Schema(description = "ID of the lecturer", example = "1")
    Integer state;

    @AssertTrue(message = "Due date must be after the start date")
    public boolean isDueDateAfterStartDate() {
        if (startDate == null || dueDate == null) {
            return true;
        }
        return dueDate.isAfter(startDate);
    }
}
