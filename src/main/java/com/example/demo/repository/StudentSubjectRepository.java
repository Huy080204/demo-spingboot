package com.example.demo.repository;

import com.example.demo.model.entity.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {
}
