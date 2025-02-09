package com.example.demo.model.scheduler;

import com.example.demo.model.entity.StudentSubject;
import com.example.demo.model.entity.Subject;
import com.example.demo.repository.StudentSubjectRepository;
import com.example.demo.repository.SubjectRepository;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Data
@Slf4j
@Component
public class StudentSubjectScheduler {
    StudentSubjectRepository studentSubjectRepository;
    SubjectRepository subjectRepository;

    @Scheduled(fixedRate = 60000) // 1p
    public void updateStudentSubjectStatus() {
        List<Subject> subjects = subjectRepository.findAll();

        for (Subject subject : subjects) {
            List<StudentSubject> studentSubjects = studentSubjectRepository.findBySubject(subject);

            boolean allDone = studentSubjects.stream().allMatch(StudentSubject::isDone);

            log.info("Subject: {} - {}", subject.getName(), allDone ? "All students are done" : "Not all students are done.");
        }
    }
}
