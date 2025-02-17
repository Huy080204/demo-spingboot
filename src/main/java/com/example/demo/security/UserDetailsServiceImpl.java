package com.example.demo.security;

import com.example.demo.enumeration.ErrorCode;
import com.example.demo.exception.AppException;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    UserRepository userRepository;
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        com.example.demo.model.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        List<GrantedAuthority> authorities = user.getRole().getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPCode()))
                .collect(Collectors.toList());

        boolean superAdmin = user.getRole().getName().equals("ADMIN")
                && adminRepository.existsByUserUsernameAndSuperAdmin(user.getUsername(), true);

        return new CustomUserDetails(user.getUsername(), user.getPassword(), user.getFullName(), user.getAvatar(), superAdmin, authorities);
    }
}
