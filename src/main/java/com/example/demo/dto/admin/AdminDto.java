package com.example.demo.dto.admin;

import com.example.demo.enumeration.Gender;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
public class AdminDto {

    Long adminId;
    String username;
    Integer level;
    boolean superAdmin;
    Gender gender;

}
