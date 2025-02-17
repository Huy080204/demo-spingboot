package com.example.demo.service;

import com.example.demo.dto.admin.AdminDto;
import com.example.demo.form.admin.CreateAdminForm;
import com.example.demo.security.CustomUserDetails;

import java.util.List;

public interface AdminService {

    AdminDto createAdmin(CreateAdminForm request, CustomUserDetails userDetails);

    List<AdminDto> getAllAdmins();

}
