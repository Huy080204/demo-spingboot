package com.example.demo.mapper;

import com.example.demo.dto.admin.AdminDto;
import com.example.demo.form.admin.CreateAdminForm;
import com.example.demo.model.Admin;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = GenderMapper.class)
public interface AdminMapper {
    @Mapping(source = "username", target = "user.username")
    @Mapping(source = "password", target = "user.password")
    @Mapping(source = "fullName", target = "user.fullName")
    @Mapping(source = "gender", target = "user.gender", qualifiedByName = "genderToInteger")
    @Mapping(source = "superAdmin", target = "superAdmin")
    Admin toAdmin(CreateAdminForm request);

    @Mapping(source = "id", target = "adminId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.gender", target = "gender", qualifiedByName = "integerToGender")
    AdminDto toAdminDto(Admin admin);

    @IterableMapping(elementTargetType = AdminDto.class)
    List<AdminDto> toAdminDtoList(List<Admin> admins);

}
