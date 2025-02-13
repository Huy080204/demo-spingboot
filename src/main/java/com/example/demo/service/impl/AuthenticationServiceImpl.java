package com.example.demo.service.impl;

import com.example.demo.dto.user.UserDto;
import com.example.demo.form.authentication.AuthenticationForm;
import com.example.demo.form.authentication.IntrospectForm;
import com.example.demo.dto.authentication.AuthenticationDto;
import com.example.demo.dto.authentication.IntrospectDto;
import com.example.demo.service.AuthenticationService;
import com.example.demo.util.JwtUtil;
import com.nimbusds.jose.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    AuthenticationManager authenticationManager;
    JwtUtil jwtUtil;

    @Override
    public AuthenticationDto authenticate(AuthenticationForm request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            List<String> authorities = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtUtil.generateToken(userDetails.getUsername(), authorities);

            Claims claims = Jwts.parser()
                    .setSigningKey(jwtUtil.getSIGNER_KEY().getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            String fullName = (String) claims.get("fullName");
            String avatar = (String) claims.get("avatar");

            UserDto userDto = UserDto.builder()
                    .username(username)
                    .fullName(fullName)
                    .avatar(avatar)
                    .build();

            return new AuthenticationDto(token, userDto, authorities);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }

}
