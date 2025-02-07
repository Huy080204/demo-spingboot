package com.example.demo.mapper;

import com.example.demo.dto.studentSubject.StudentSubjectDto;
import com.example.demo.model.entity.StudentSubject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, SubjectMapper.class})
public interface StudentSubjectMapper {
    @Mapping(source = "id", target = "studentSubjectId")
    @Mapping(source = "student", target = "studentResponse")
    @Mapping(source = "subject", target = "subjectResponse", qualifiedByName = "toSubjectDto")
    StudentSubjectDto toStudentSubjectDto(StudentSubject studentSubject);
}
