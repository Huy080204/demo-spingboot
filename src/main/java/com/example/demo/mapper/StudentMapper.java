package com.example.demo.mapper;

import com.example.demo.enumeration.Gender;
import com.example.demo.form.student.CreateStudentForm;
import com.example.demo.form.student.UpdateStudentForm;
import com.example.demo.dto.student.StudentDto;
import com.example.demo.model.Student;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(source = "dateOfBirth", target = "birthDate")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "genderToInteger")
    Student toStudent(CreateStudentForm request);

    @Mapping(source = "id", target = "studentId")
    @Mapping(source = "birthDate", target = "dateOfBirth")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "integerToGender")
    StudentDto toStudentResponse(Student student);

    @IterableMapping(elementTargetType = StudentDto.class)
    List<StudentDto> toStudentResponseList(List<Student> students);

    @Mapping(source = "dateOfBirth", target = "birthDate")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "genderToInteger")
    void updateStudent(@MappingTarget Student student, UpdateStudentForm request);

    @Named("genderToInteger")
    static Integer genderToInteger(Gender gender) {
        return (gender != null) ? gender.getValue() : null;
    }

    @Named("integerToGender")
    static Gender integerToGender(Integer gender) {
        return (gender != null) ? Gender.fromInt(gender) : null;
    }
}
