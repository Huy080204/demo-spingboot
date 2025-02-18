package com.example.demo.service;

import com.example.demo.dto.lecturer.LecturerDto;
import com.example.demo.form.lecturer.CreateLecturerForm;

public interface LecturerService {
    LecturerDto createLecturer(CreateLecturerForm form);
}
