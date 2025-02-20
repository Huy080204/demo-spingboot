package com.example.demo.mapper;

import com.example.demo.enumeration.Gender;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface GenderMapper {

    @Named("genderToInteger")
    static Integer genderToInteger(Gender gender) {
        return (gender != null) ? gender.getValue() : null;
    }

    @Named("integerToGender")
    static Gender integerToGender(Integer gender) {
        return (gender != null) ? Gender.fromInt(gender) : null;
    }

}
