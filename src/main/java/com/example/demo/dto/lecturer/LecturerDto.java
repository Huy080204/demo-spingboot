package com.example.demo.dto.lecturer;

import com.example.demo.enumeration.Gender;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
public class LecturerDto {
    Long lecturerId;
    String username;
    String fullName;
    String career;
    Gender gender;
}
