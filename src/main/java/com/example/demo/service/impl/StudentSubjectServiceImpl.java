package com.example.demo.service.impl;

import com.example.demo.dto.report.ReportAcademicDto;
import com.example.demo.dto.studentSubject.StudentSubjectDto;
import com.example.demo.enumeration.ErrorCode;
import com.example.demo.enumeration.StudentSubjectStatus;
import com.example.demo.exception.AppException;
import com.example.demo.mapper.StudentSubjectMapper;
import com.example.demo.model.Student;
import com.example.demo.model.StudentSubject;
import com.example.demo.model.Subject;
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
    public StudentSubjectDto createStudentSubject(Student student, Subject subject) {
        StudentSubject studentSubject = StudentSubject.builder()
                .student(student)
                .subject(subject)
                .dateRegister(LocalDate.now())
                .status(StudentSubjectStatus.PENDING)
                .build();

        return studentSubjectMapper.toStudentSubjectDto(studentSubjectRepository.save(studentSubject));
    }

    @Override
    public StudentSubject getStudentSubjectById(Long id) {
        return studentSubjectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public StudentSubjectDto getStudentSubjectResponseById(Long id) {
        return studentSubjectMapper.toStudentSubjectDto(studentSubjectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    @Override
    public StudentSubjectDto updateStudentSubject(Student student, Subject subject, StudentSubjectStatus status, Boolean done) {
        StudentSubject studentSubject = studentSubjectRepository
                .findByStudentAndSubject(student, subject)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        boolean updated = false;

        if (status != null && !status.equals(studentSubject.getStatus())) {
            studentSubject.setStatus(status);
            updated = true;
        }
        if (done != null && done != studentSubject.isDone()) {
            studentSubject.setDone(done);
            updated = true;
        }

        if (updated) {
            studentSubject = studentSubjectRepository.save(studentSubject);
        }
        return studentSubjectMapper.toStudentSubjectDto(studentSubject);
    }

    @Override
    public void deleteStudentSubjectById(Long id) {
        getStudentSubjectResponseById(id);

        studentSubjectRepository.deleteById(id);
    }

    @Override
    public ReportAcademicDto getAcademicReport() {
        Object rawResult = studentSubjectRepository.getAcademicReport();

        if (!(rawResult instanceof Object[] rawArray) || rawArray.length == 0 || !(rawArray[0] instanceof Object[])) {
            throw new AppException(ErrorCode.INVALID_FORM);
        }

        Object[] result = (Object[]) rawArray[0];

        return ReportAcademicDto.builder()
                .totalCourses(((Number) result[0]).intValue())
                .totalStudents(((Number) result[1]).intValue())
                .maleStudents(((Number) result[2]).intValue())
                .femaleStudents(((Number) result[3]).intValue())
                .build();
    }


}
