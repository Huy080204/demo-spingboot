package com.example.demo.service.impl;

import com.example.demo.dto.request.subject.SubjectCreateRequest;
import com.example.demo.dto.request.subject.SubjectUpdateRequest;
import com.example.demo.dto.response.subject.SubjectResponse;
import com.example.demo.entity.Student;
import com.example.demo.entity.Subject;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.SubjectMapper;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.service.SubjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {
    SubjectRepository subjectRepository;
    SubjectMapper subjectMapper;

    @Override
    public SubjectResponse createSubject(SubjectCreateRequest request) {
        if (subjectRepository.existsByCode(request.getSubjectCode())) {
            throw new AppException(ErrorCode.SUBJECT_CODE_EXITED);
        }

        Subject subject = subjectMapper.toSubject(request);

        return subjectMapper.toSubjectResponse(subjectRepository.save(subject));
    }

    @Override
    public List<SubjectResponse> getAllSubjects() {
        return subjectMapper.toSubjectResponseList(subjectRepository.findAll());
    }

    @Override
    public SubjectResponse getSubjectResponseById(Long id) {
        return subjectMapper.toSubjectResponse(subjectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SUBJECT_NOT_FOUND)));
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SUBJECT_NOT_FOUND));
    }

    @Override
    public SubjectResponse updateSubject(Long id, SubjectUpdateRequest request) {
        Subject subject = getSubjectById(id);

        subjectMapper.updateSubject(subject, request);

        return subjectMapper.toSubjectResponse(subjectRepository.save(subject));
    }

    @Override
    public void deleteSubject(Long id) {
        getSubjectById(id);
        subjectRepository.deleteById(id);
    }

    @Override
    public void addStudentToSubject(Subject subject, Student student) {
        if (subject.getStudents().contains(student)) {
            throw new AppException(ErrorCode.STUDENT_ALREADY_ENROLLED);
        }
        subject.getStudents().add(student);
        subjectRepository.save(subject);
    }

    @Override
    public void deleteSubjectForStudent(Long id, Student student) {
        Subject subject = getSubjectById(id);
        if (!subject.getStudents().contains(student)) {
            throw new AppException(ErrorCode.STUDENT_NOT_FOUND);
        }
        subject.getStudents().remove(student);
        subjectRepository.save(subject);
    }
}
