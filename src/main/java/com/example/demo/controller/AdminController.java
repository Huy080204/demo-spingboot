package com.example.demo.controller;

import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.admin.AdminDto;
import com.example.demo.form.admin.CreateAdminForm;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/admin")
@Tag(name = "Admin Controller")
public class AdminController {

    AdminService adminService;

    // create
    @PostMapping(path = "/create")
    public ResponseEntity<ApiMessageDto<AdminDto>> create(
            @RequestBody @Valid CreateAdminForm request
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        AdminDto adminDto = adminService.createAdmin(request, userDetails);

        ApiMessageDto<AdminDto> response = ApiMessageDto.<AdminDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Admin created successfully")
                .data(adminDto)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // get all
    @GetMapping(path = "/list-all")
    public ResponseEntity<ApiMessageDto<List<AdminDto>>> getAllStudents() {
        List<AdminDto> studentResponses = adminService.getAllAdmins();

        ApiMessageDto<List<AdminDto>> response = ApiMessageDto.<List<AdminDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get all admins successfully")
                .data(studentResponses)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
