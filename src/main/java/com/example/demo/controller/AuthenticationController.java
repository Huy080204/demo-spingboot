package com.example.demo.controller;

import com.example.demo.form.authentication.AuthenticationForm;
import com.example.demo.dto.ApiMessageDto;
import com.example.demo.dto.authentication.AuthenticationDto;
import com.example.demo.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/log-in")
    public ResponseEntity<ApiMessageDto<AuthenticationDto>> authenticate(@Valid @RequestBody AuthenticationForm authenticationRequest) {
        AuthenticationDto authenticationResponse = authenticationService.authenticate(authenticationRequest);

        ApiMessageDto<AuthenticationDto> response = ApiMessageDto.<AuthenticationDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Login successfully")
                .data(authenticationResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
