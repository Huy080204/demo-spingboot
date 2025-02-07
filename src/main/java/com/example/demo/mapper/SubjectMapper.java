package com.example.demo.mapper;

import com.example.demo.dto.subject.SubjectDto;
import com.example.demo.form.subject.CreateSubjectForm;
import com.example.demo.form.subject.UpdateSubjectForm;
import com.example.demo.model.entity.Subject;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    @Mapping(source = "subjectName", target = "name")
    @Mapping(source = "subjectCode", target = "code")
    Subject toSubject(CreateSubjectForm request);

    @Mapping(source = "id", target = "subjectId")
    @Mapping(source = "name", target = "subjectName")
    @Mapping(source = "code", target = "subjectCode")
    @Named("toSubjectDto")
    SubjectDto toSubjectResponse(Subject subject);

    @IterableMapping(elementTargetType = SubjectDto.class)
    List<SubjectDto> toSubjectResponseList(List<Subject> subjects);

    @Mapping(source = "subjectName", target = "name")
    void updateSubject(@MappingTarget Subject subject, UpdateSubjectForm request);
}
