package com.example.demo.constant;

import com.example.demo.enumeration.Gender;

import java.util.EnumSet;
import java.util.Set;

public class GenderConstants {
    public static final Set<Gender> VALID_GENDERS = EnumSet.allOf(Gender.class);
}
