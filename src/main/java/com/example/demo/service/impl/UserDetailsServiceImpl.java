package com.example.demo.service.impl;

import com.example.demo.constant.Authorities;
import com.example.demo.enumeration.ErrorCode;
import com.example.demo.exception.AppException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        List<GrantedAuthority> authorities = Authorities.DEFAULT_AUTHORITIES;

        return new User(student.getUsername(), student.getPassword(), authorities);
    }
}
