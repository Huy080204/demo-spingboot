package com.example.demo.model.entity;

import com.example.demo.enumeration.StudentSubjectStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "student_subject")
public class StudentSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    Student student;

    @ManyToOne
    @JoinColumn(nullable = false)
    Subject subject;

    @Column(nullable = false)
    LocalDate dateRegister;

    @Column(nullable = false)
    StudentSubjectStatus status;
}
