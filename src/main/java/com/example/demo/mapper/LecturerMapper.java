package com.example.demo.mapper;

import com.example.demo.dto.lecturer.LecturerDto;
import com.example.demo.form.lecturer.CreateLecturerForm;
import com.example.demo.model.Lecturer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = GenderMapper.class)
public interface LecturerMapper {

    @Mapping(source = "username", target = "user.username")
    @Mapping(source = "password", target = "user.password")
    @Mapping(source = "fullName", target = "user.fullName")
    @Mapping(source = "gender", target = "user.gender", qualifiedByName = "genderToInteger")
    Lecturer toEntity(CreateLecturerForm form);

    @Mapping(source = "id", target = "studentId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.fullName", target = "fullName")
    @Mapping(source = "user.gender", target = "gender", qualifiedByName = "integerToGender")
    @Mapping(source = "id", target = "lecturerId")
    LecturerDto toDto(Lecturer entity);

}
