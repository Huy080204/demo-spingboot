package com.example.demo.util;

import com.example.demo.enumeration.ErrorCode;
import com.example.demo.exception.AppException;
import com.example.demo.model.User;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Component
public class JwtUtil {
    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    public String generateToken(String username, List<String> authorities) {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        boolean isSuperAdmin =
                user.getRole().getName().equals("ADMIN")
                        && adminRepository.existsByUserUsernameAndSuperAdmin(user.getUsername(), true);

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getFullName());
        claims.put("avatar", user.getAvatar());
        claims.put("superAdmin", isSuperAdmin);

        claims.put("authorities", authorities);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .claim("authorities", authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // Token 24h
                .signWith(SignatureAlgorithm.HS256, SIGNER_KEY.getBytes())
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNER_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public List<String> extractAuthorities(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SIGNER_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return (List<String>) claims.get("authorities", List.class);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNER_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNER_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims extractClaimsFromToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token format");
        }
        String token = authHeader.substring(7);
        return parseToken(token);
    }

}
