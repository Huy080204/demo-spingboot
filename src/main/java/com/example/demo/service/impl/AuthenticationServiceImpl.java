package com.example.demo.service.impl;

import com.example.demo.form.authentication.AuthenticationForm;
import com.example.demo.form.authentication.IntrospectForm;
import com.example.demo.dto.authentication.AuthenticationDto;
import com.example.demo.dto.authentication.IntrospectDto;
import com.example.demo.service.AuthenticationService;
import com.example.demo.util.JwtUtil;
import com.nimbusds.jose.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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

            return new AuthenticationDto(token, userDetails.getUsername(), authorities);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }

    @Override
    public IntrospectDto introspect(IntrospectForm request) throws JOSEException, ParseException {
//        String token = request.getToken();
//        JWSVerifier verifier = new MACVerifier(SIGNER_KEY);
//        SignedJWT signedJWT = SignedJWT.parse(token);
//        Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
//        boolean verified = signedJWT.verify(verifier);
//        return IntrospectDto.builder()
//                .valid(verified && expiration.after(new Date()))
//                .build();
        return null;
    }

    private String generateToken(String username) {
//        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
//
//        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
//                .subject(username)
//                .issuer("demo.com")
//                .issueTime(new Date())
//                .expirationTime(new Date(
//                        Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli())
//                )
//                .claim("authorities", "ROLE_USER")
//                .build();
//
//        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
//
//        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
//        try {
//            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
//            return jwsObject.serialize();
//        } catch (JOSEException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }
}
