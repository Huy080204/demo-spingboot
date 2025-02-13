package com.example.demo.constant;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class Authorities {
    public static final List<GrantedAuthority> DEFAULT_AUTHORITIES = List.of(
            new SimpleGrantedAuthority("STU_GET"),
            new SimpleGrantedAuthority("SUB_GET"),
             new SimpleGrantedAuthority("STU_CRE"),
            new SimpleGrantedAuthority("SUB_CRE")
    );
}
