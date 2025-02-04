package com.example.demo.service.impl;

import com.example.demo.dto.response.studentSubject.StudentSubjectResponse;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentSubject;
import com.example.demo.entity.Subject;
import com.example.demo.enumeration.ErrorCode;
import com.example.demo.enumeration.StudentSubjectStatus;
import com.example.demo.exception.AppException;
import com.example.demo.mapper.StudentSubjectMapper;
import com.example.demo.repository.StudentSubjectRepository;
import com.example.demo.service.StudentSubjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class StudentSubjectServiceImpl implements StudentSubjectService {
    StudentSubjectRepository studentSubjectRepository;
    StudentSubjectMapper studentSubjectMapper;

    @Override
    public StudentSubjectResponse createStudentSubject(Student student, Subject subject) {
        StudentSubject studentSubject = StudentSubject.builder()
                .student(student)
                .subject(subject)
                .dateRegister(LocalDate.now())
                .status(StudentSubjectStatus.PENDING)
                .build();

        return studentSubjectMapper.toStudentSubjectResponse(studentSubjectRepository.save(studentSubject));
    }

    @Override
    public StudentSubject getStudentSubjectById(Long id) {
        return studentSubjectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public StudentSubjectResponse getStudentSubjectResponseById(Long id) {
        return studentSubjectMapper.toStudentSubjectResponse(studentSubjectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    @Override
    public StudentSubjectResponse updateStudentSubject(Long id) {
        StudentSubject studentSubject = getStudentSubjectById(id);
        studentSubject.setStatus(StudentSubjectStatus.ACTIVE);
        return studentSubjectMapper.toStudentSubjectResponse(studentSubjectRepository.save(studentSubject));
    }

    @Override
    public void deleteStudentSubjectById(Long id) {
        getStudentSubjectResponseById(id);

        studentSubjectRepository.deleteById(id);
    }

}
