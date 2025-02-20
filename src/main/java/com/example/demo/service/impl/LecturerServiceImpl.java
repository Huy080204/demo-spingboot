package com.example.demo.service.impl;

import com.example.demo.dto.PageResponseDto;
import com.example.demo.dto.lecturer.LecturerDto;
import com.example.demo.enumeration.ErrorCode;
import com.example.demo.exception.AppException;
import com.example.demo.form.lecturer.CreateLecturerForm;
import com.example.demo.form.lecturer.UpdateLecturerForm;
import com.example.demo.mapper.LecturerMapper;
import com.example.demo.model.Lecturer;
import com.example.demo.model.Role;
import com.example.demo.model.criteria.LecturerCriteria;
import com.example.demo.repository.LecturerRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LecturerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class LecturerServiceImpl implements LecturerService {

    UserRepository userRepository;
    LecturerRepository lecturerRepository;
    RoleRepository roleRepository;

    LecturerMapper lecturerMapper;

    PasswordEncoder passwordEncoder;

    @Override
    public LecturerDto createLecturer(CreateLecturerForm form) {
        if (userRepository.existsByUsername(form.getUsername())) {
            throw new AppException(ErrorCode.USER_EXITED);
        }

        Lecturer lecturer = lecturerMapper.toEntity(form);

        lecturer.getUser().setPassword(passwordEncoder.encode(form.getPassword()));

        Role lecturerRole = roleRepository.findByName("LECTURER")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        lecturer.getUser().setRole(lecturerRole);

        return lecturerMapper.toDto(lecturerRepository.save(lecturer));
    }

    @Override
    public LecturerDto getLecturer(Long id) {
        Lecturer lecturer = lecturerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return lecturerMapper.toDto(lecturer);
    }

    @Override
    public PageResponseDto<LecturerDto> getPageLecturers(LecturerCriteria criteria, Pageable pageable) {
        Page<Lecturer> pageData = lecturerRepository.findAll(criteria.getCriteria(), pageable);

        return PageResponseDto.<LecturerDto>builder()
                .currentPage(pageable.getPageNumber())
                .totalPages(pageData.getTotalPages())
                .pageSize(pageable.getPageSize())
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent().stream()
                        .map(lecturerMapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public LecturerDto updateLecturer(UpdateLecturerForm form) {
        Lecturer lecturer = lecturerRepository.findById(form.getLecturerId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        lecturerMapper.update(lecturer, form);
        return lecturerMapper.toDto(lecturerRepository.save(lecturer));
    }

    @Override
    public void deleteLecturer(Long id) {
        Lecturer lecturer = lecturerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        lecturerRepository.delete(lecturer);
    }
}
