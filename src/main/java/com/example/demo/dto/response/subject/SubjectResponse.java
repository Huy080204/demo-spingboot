package com.example.demo.dto.response.subject;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
public class SubjectResponse {
    Long subjectId;
    String subjectCode;
    String subjectName;
}
