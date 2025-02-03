package com.example.demo.controller;

import com.example.demo.dto.request.authentication.AuthenticationRequest;
import com.example.demo.dto.request.authentication.IntrospectRequest;
import com.example.demo.dto.response.APIResponse;
import com.example.demo.dto.response.authentication.AuthenticationResponse;
import com.example.demo.dto.response.authentication.IntrospectResponse;
import com.example.demo.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/log-in")
    public ResponseEntity<APIResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);

        APIResponse<AuthenticationResponse> response = APIResponse.<AuthenticationResponse>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Login successfully")
                .data(authenticationResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/introspect")
    public ResponseEntity<APIResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        IntrospectResponse introspectResponse = authenticationService.introspect(introspectRequest);

        APIResponse<IntrospectResponse> response = APIResponse.<IntrospectResponse>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Login successfully")
                .data(introspectResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
