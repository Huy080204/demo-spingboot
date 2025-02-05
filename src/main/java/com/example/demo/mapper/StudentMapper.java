package com.example.demo.mapper;

import com.example.demo.dto.request.student.StudentCreateRequest;
import com.example.demo.dto.request.student.StudentUpdateRequest;
import com.example.demo.dto.response.student.StudentResponse;
import com.example.demo.dto.response.subject.SubjectResponse;
import com.example.demo.entity.Student;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(source = "dateOfBirth", target = "birthDate")
    Student toStudent(StudentCreateRequest request);

    @Mapping(source = "id", target = "studentId")
    @Mapping(source = "birthDate", target = "dateOfBirth")
    StudentResponse toStudentResponse(Student student);

    @IterableMapping(elementTargetType = StudentResponse.class)
    List<StudentResponse> toStudentResponseList(List<Student> students);

    @Mapping(source = "dateOfBirth", target = "birthDate")
    void updateStudent(@MappingTarget Student student, StudentUpdateRequest request);
}
