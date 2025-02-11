package com.example.demo.repository;

import com.example.demo.model.Student;
import com.example.demo.model.StudentSubject;
import com.example.demo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {

    List<StudentSubject> findBySubject(Subject subject);

    Optional<StudentSubject> findByStudentAndSubject(Student student, Subject subject);

    @Query("""
                 SELECT ss.subject.id
                 FROM StudentSubject ss
                 GROUP BY ss.subject.id
                 HAVING SUM(CASE WHEN ss.done = false THEN 1 ELSE 0 END) = 0
            """)
    List<Long> findSubjectsWithAllStudentsDone();

}
