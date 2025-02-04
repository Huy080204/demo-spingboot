package com.example.demo.enumeration;

import lombok.Getter;

@Getter
public enum StudentSubjectStatus {
    PENDING("Pending"),
    ACTIVE("Active");
    private final String displayName;

    StudentSubjectStatus(String displayName) {
        this.displayName = displayName;
    }
}
