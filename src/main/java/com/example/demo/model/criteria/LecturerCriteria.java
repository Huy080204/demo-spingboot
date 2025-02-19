package com.example.demo.model.criteria;

import com.example.demo.model.Lecturer;
import com.example.demo.model.User;
import jakarta.persistence.criteria.*;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class LecturerCriteria {
    Long id;
    String career;
    String username;
    String fullName;

    public Specification<Lecturer> getCriteria() {
        return new Specification<Lecturer>() {
            @Override
            public Predicate toPredicate(Root<Lecturer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                // by id
                Optional.ofNullable(id)
                        .ifPresent(value -> predicates.add(cb.equal(root.get("id"), value)));

                // by career
                Optional.ofNullable(career)
                        .filter(c -> !c.trim().isEmpty())
                        .ifPresent(value -> predicates.add(cb.like(root.get("career"), "%" + value + "%")));

                boolean needJoinUser = (username != null && !username.trim().isEmpty()) ||
                        (fullName != null && !fullName.trim().isEmpty());

                if (needJoinUser) {
                    Join<Lecturer, User> lecturerUserJoin = root.join("user");

                    // by username
                    Optional.ofNullable(username)
                            .filter(u -> !u.trim().isEmpty())
                            .ifPresent(value -> predicates.add(cb.like(lecturerUserJoin.get("username"), "%" + value + "%")));

                    // by fullName
                    Optional.ofNullable(fullName)
                            .filter(f -> !f.trim().isEmpty())
                            .ifPresent(value -> predicates.add(cb.like(lecturerUserJoin.get("fullName"), "%" + value + "%")));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
