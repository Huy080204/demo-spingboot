package com.example.demo.form.lecturerSchduler;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "CreateLecturerSchedulerForm", description = "Request body for creating a lecturer scheduler")
public class CreateLecturerSchedulerForm {
    @NotNull(message = "Lecture id can not be null or empty")
    @Schema(description = "ID of the lecturer", example = "1")

    Long lecturerId;
    @NotNull(message = "Subject id can not be null or empty")
    @Schema(description = "ID of the subject", example = "1")

    Long subjectId;
    @NotNull(message = "Period id can not be null or empty")
    @Schema(description = "ID of the period", example = "1")
    Long periodId;
}
