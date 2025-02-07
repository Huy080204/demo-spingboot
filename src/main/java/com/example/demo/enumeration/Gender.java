package com.example.demo.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Gender {
    MALE(1),
    FEMALE(2),
    OTHER(3);

    private final int value;

    Gender(int value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return name();
    }

    @JsonCreator
    public static Gender fromValue(String name) {
        for (Gender gender : values()) {
            if (gender.name().equalsIgnoreCase(name)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid gender value: " + name);
    }

    public static Gender fromInt(int value) {
        for (Gender gender : values()) {
            if (gender.value == value) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid gender code: " + value);
    }
}

