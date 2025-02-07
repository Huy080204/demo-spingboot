package com.example.demo.model.entity;

import com.example.demo.enumeration.Gender;
import com.example.demo.validation.ValidGender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String fullName;

    @Column(nullable = false)
    LocalDate birthDate;

    @Column(nullable = true)
    Integer gender;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<StudentSubject> studentSubjects = new ArrayList<>();

}
