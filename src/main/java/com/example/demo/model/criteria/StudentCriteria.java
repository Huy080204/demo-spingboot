package com.example.demo.model.criteria;

import com.example.demo.model.entity.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class StudentCriteria {
    Long id;
    String fullName;
    LocalDate birthDate;

    public Specification<Student> getCriteria() {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if (getFullName() != null && !getFullName().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("fullName"), "%" + getFullName() + "%"));
                }
                if (getBirthDate() != null) {
                    predicates.add(cb.equal(root.get("birthDate"), getBirthDate()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
