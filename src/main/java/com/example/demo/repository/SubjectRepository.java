package com.example.demo.repository;

import com.example.demo.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>, JpaSpecificationExecutor<Subject> {
    boolean existsByCode(String code);

}
