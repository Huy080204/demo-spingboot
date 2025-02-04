package com.example.demo.mapper;

import com.example.demo.dto.response.studentSubject.StudentSubjectResponse;
import com.example.demo.entity.StudentSubject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, SubjectMapper.class})
public interface StudentSubjectMapper {
    @Mapping(source = "id", target = "studentSubjectId")
    @Mapping(source = "student", target = "studentResponse")
    @Mapping(source = "subject", target = "subjectResponse")
    StudentSubjectResponse toStudentSubjectResponse(StudentSubject studentSubject);
}
