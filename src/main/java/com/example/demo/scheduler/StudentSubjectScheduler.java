package com.example.demo.scheduler;

import com.example.demo.repository.StudentSubjectRepository;
import com.example.demo.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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

    @Scheduled(cron = "0 0 1 * * *") // 1am
    @Transactional
    public void updateStudentSubjectStatus() {
        subjectRepository.updateSubjectsDoneStatus();
        log.info("Updated subjects to done = true");
    }
}
