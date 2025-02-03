package com.example.demo.mapper;

import com.example.demo.dto.request.subject.SubjectCreateRequest;
import com.example.demo.dto.request.subject.SubjectUpdateRequest;
import com.example.demo.dto.response.subject.SubjectResponse;
import com.example.demo.entity.Subject;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    @Mapping(source = "subjectName", target = "name")
    @Mapping(source = "subjectCode", target = "code")
    Subject toSubject(SubjectCreateRequest request);

    @Mapping(source = "id", target = "subjectId")
    @Mapping(source = "name", target = "subjectName")
    @Mapping(source = "code", target = "subjectCode")
    SubjectResponse toSubjectResponse(Subject subject);

    @IterableMapping(elementTargetType = SubjectResponse.class)
    List<SubjectResponse> toSubjectResponseList(List<Subject> subjects);

    @Mapping(source = "subjectName", target = "name")
    void updateSubject(@MappingTarget Subject subject, SubjectUpdateRequest request);
}
