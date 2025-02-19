package com.example.demo.service;

import com.example.demo.dto.PageResponseDto;
import com.example.demo.dto.lecturer.LecturerDto;
import com.example.demo.form.lecturer.CreateLecturerForm;
import com.example.demo.form.lecturer.UpdateLecturerForm;
import com.example.demo.model.criteria.LecturerCriteria;
import org.springframework.data.domain.Pageable;

public interface LecturerService {
    LecturerDto createLecturer(CreateLecturerForm form);

    LecturerDto getLecturer(Long id);

    PageResponseDto<LecturerDto> getPageLecturers(LecturerCriteria criteria, Pageable pageable);

    LecturerDto updateLecturer(UpdateLecturerForm form);

    void deleteLecturer(Long id);
}
