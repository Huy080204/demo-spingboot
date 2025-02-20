package com.example.demo.form.period;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Schema(name = "UpdatePeriodForm", description = "Request body for updating a period")
public class UpdatePeriodForm {

    @NotNull(message = "Period id cannot be null or empty")
    @Schema(description = "Id of the period", example = "1")
    Long id;

    @NotBlank(message = "Period name cannot be null or empty")
    @Schema(description = "Name of the period", example = "Spring Semester 2025")
    String name;

    @NotBlank(message = "Period description cannot be null or empty")
    @Schema(description = "Detailed description of the period", example = "This period covers all courses from January to June.")
    String description;

    @NotNull
    @Future(message = "Start date  must be in future")
    @Schema(description = "Start date of the period (must be in the future)", example = "2025-01-15")
    LocalDate startDate;

    @NotNull
    @Future(message = "Due date  must be in future")
    @Schema(description = "Start due of the period (must be in the future)", example = "2025-01-15")
    LocalDate dueDate;

    @NotNull
    @Min(value = 1, message = "State must be at least 1")
    @Max(value = 3, message = "State cannot be greater than 3")
    @Schema(description = "State of the period (must be 1, 2, or 3)", example = "1")
    Integer state;

    @AssertTrue(message = "Due date must be after the start date")
    public boolean isDueDateAfterStartDate() {
        if (startDate == null || dueDate == null) {
            return true;
        }
        return dueDate.isAfter(startDate);
    }
}
