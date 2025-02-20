package com.example.demo.form.lecturerSchduler;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "UpdateLecturerSchedulerForm", description = "Request body for updating a lecturer scheduler")
public class UpdateLecturerSchedulerForm {
    @NotNull(message = "Lecture scheduler id can not be null or empty")
    @Schema(description = "ID of the lecture scheduler", example = "1")
    Long lectureSchedulerId;

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
