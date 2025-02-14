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

    @Query("""
            SELECT
                COALESCE(COUNT(DISTINCT subj.id), 0),
                COALESCE(COUNT(DISTINCT s.id), 0),
                COALESCE(SUM(CASE WHEN u.gender = 1 THEN 1 ELSE 0 END), 0),
                COALESCE(SUM(CASE WHEN u.gender = 2 THEN 1 ELSE 0 END), 0)
            FROM StudentSubject ss
            LEFT JOIN Student s ON ss.student.id = s.id
            LEFT JOIN Subject subj ON ss.subject.id = subj.id
            LEFT JOIN User u ON s.user.id = u.id
            """)
    Object[] getAcademicReport();


}
