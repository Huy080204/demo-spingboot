package com.example.demo.service.impl;

import com.example.demo.dto.admin.AdminDto;
import com.example.demo.enumeration.ErrorCode;
import com.example.demo.exception.AppException;
import com.example.demo.form.admin.CreateAdminForm;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.model.Admin;
import com.example.demo.model.Role;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.AdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    AdminRepository adminRepository;
    UserRepository userRepository;
    RoleRepository roleRepository;

    AdminMapper adminMapper;

    PasswordEncoder passwordEncoder;

    @Override
    public AdminDto createAdmin(CreateAdminForm request, CustomUserDetails userDetails) {
        boolean isSuperAdmin = userDetails.isSuperAdmin();

        if (!isSuperAdmin) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXITED);
        }

        Admin admin = adminMapper.toAdmin(request);

        admin.getUser().setPassword(passwordEncoder.encode(request.getPassword()));

        Role studentRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        admin.getUser().setRole(studentRole);

        return adminMapper.toAdminDto(adminRepository.save(admin));
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        return adminMapper.toAdminDtoList(adminRepository.findAll());
    }
}
