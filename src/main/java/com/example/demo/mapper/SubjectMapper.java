package com.example.demo.mapper;

import com.example.demo.dto.request.StudentUpdateRequest;
import com.example.demo.dto.request.SubjectCreateRequest;
import com.example.demo.dto.request.SubjectUpdateRequest;
import com.example.demo.dto.response.SubjectResponse;
import com.example.demo.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    Subject toSubject(SubjectCreateRequest request);

    SubjectResponse toSubjectResponse(Subject subject);

    List<SubjectResponse> toSubjectResponseList(List<Subject> subjects);

    void updateSubject(@MappingTarget Subject subject, SubjectUpdateRequest request);
}
