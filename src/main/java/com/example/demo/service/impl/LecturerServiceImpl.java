package com.example.demo.service.impl;

import com.example.demo.dto.lecturer.LecturerDto;
import com.example.demo.enumeration.ErrorCode;
import com.example.demo.exception.AppException;
import com.example.demo.form.lecturer.CreateLecturerForm;
import com.example.demo.mapper.LecturerMapper;
import com.example.demo.model.Lecturer;
import com.example.demo.model.Role;
import com.example.demo.repository.LecturerRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LecturerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
