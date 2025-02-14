package com.example.demo.repository;

import com.example.demo.model.Student;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    boolean existsByUserUsername(String username);

    Optional<Student> findByUserUsername(String username);
}
