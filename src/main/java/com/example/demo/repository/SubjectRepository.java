package com.example.demo.repository;

import com.example.demo.model.Subject;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>, JpaSpecificationExecutor<Subject> {
    boolean existsByCode(String code);

    @Modifying
    @Transactional
    @Query("""
              UPDATE Subject s
              SET s.done = true
              WHERE s.id IN (
              SELECT ss.subject.id
                   FROM StudentSubject ss
                   GROUP BY ss.subject.id
                   HAVING SUM(CASE WHEN ss.done = false THEN 1 ELSE 0 END) = 0
              )
            \s""")
    void updateSubjectsDoneStatus();
}
