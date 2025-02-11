package com.example.demo.model.criteria;

import com.example.demo.model.Student;
import com.example.demo.model.StudentSubject;
import com.example.demo.model.Subject;
import jakarta.persistence.criteria.*;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectCriteria {
    Long id;
    String name;
    Long studentId;

    public Specification<Subject> getCriteria() {
        return new Specification<Subject>() {
            @Override
            public Predicate toPredicate(Root<Subject> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if (getName() != null && !getName().trim().isEmpty()) {
                    String normalizedName = getName().trim().toLowerCase();
                    predicates.add(cb.like(cb.lower(root.get("name")), "%" + normalizedName + "%"));
                }
                if (getStudentId() != null) {
                    Join<Subject, StudentSubject> studentSubjectJoin = root.join("studentSubjects");
                    Join<StudentSubject, Student> studentJoin = studentSubjectJoin.join("student");
                    predicates.add(cb.equal(studentJoin.get("id"), studentId));
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
