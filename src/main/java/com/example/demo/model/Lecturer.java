package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Lecturer {
    @Id
    Long id;

    @Column(nullable = false)
    String career;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @MapsId
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    User user;

}
