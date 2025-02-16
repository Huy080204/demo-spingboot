package com.example.demo.repository;

import com.example.demo.model.Student;
import com.example.demo.repository.projection.StudentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    @Query("""
                SELECT s.id AS studentId, u.username AS username, u.fullName AS fullName
                FROM Student s
                LEFT JOIN s.user u
            """)
    List<StudentProjection> findAllProjected();

}
