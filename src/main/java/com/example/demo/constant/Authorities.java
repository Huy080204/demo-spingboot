package com.example.demo.constant;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class Authorities {
    public static final List<GrantedAuthority> DEFAULT_AUTHORITIES = List.of(
            new SimpleGrantedAuthority("S_GET"),
            new SimpleGrantedAuthority("SUB_GET")
    );
}
