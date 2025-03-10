package com.example.demo.mapper;

import com.example.demo.dto.student.StudentDto;
import com.example.demo.form.student.CreateStudentForm;
import com.example.demo.form.student.UpdateStudentForm;
import com.example.demo.model.Student;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = GenderMapper.class)
public interface StudentMapper {
    @Mapping(source = "username", target = "user.username")
    @Mapping(source = "password", target = "user.password")
    @Mapping(source = "fullName", target = "user.fullName")
    @Mapping(source = "gender", target = "user.gender", qualifiedByName = "genderToInteger")
    @Mapping(source = "dateOfBirth", target = "birthDate")
    Student toStudent(CreateStudentForm request);

    @Mapping(source = "id", target = "studentId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.fullName", target = "fullName")
    @Mapping(source = "user.gender", target = "gender", qualifiedByName = "integerToGender")
    @Mapping(source = "birthDate", target = "dateOfBirth")
    StudentDto toStudentResponse(Student student);

    @IterableMapping(elementTargetType = StudentDto.class)
    List<StudentDto> toStudentResponseList(List<Student> students);

    @Mapping(source = "password", target = "user.password")
    @Mapping(source = "fullName", target = "user.fullName")
    @Mapping(source = "gender", target = "user.gender", qualifiedByName = "genderToInteger")
    @Mapping(source = "dateOfBirth", target = "birthDate")
    void updateStudent(@MappingTarget Student student, UpdateStudentForm request);

}
