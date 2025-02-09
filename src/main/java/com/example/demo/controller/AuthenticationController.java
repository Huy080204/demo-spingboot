package com.example.demo.controller;

import com.example.demo.form.authentication.AuthenticationForm;
import com.example.demo.form.authentication.IntrospectForm;
import com.example.demo.dto.APIMessageDto;
import com.example.demo.dto.authentication.AuthenticationDto;
import com.example.demo.dto.authentication.IntrospectDto;
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
@RequestMapping("api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/log-in")
    public ResponseEntity<APIMessageDto<AuthenticationDto>> authenticate(@RequestBody AuthenticationForm authenticationRequest) {
        AuthenticationDto authenticationResponse = authenticationService.authenticate(authenticationRequest);

        APIMessageDto<AuthenticationDto> response = APIMessageDto.<AuthenticationDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Login successfully")
                .data(authenticationResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/introspect")
    public ResponseEntity<APIMessageDto<IntrospectDto>> introspect(@RequestBody IntrospectForm introspectRequest) throws ParseException, JOSEException {
        IntrospectDto introspectResponse = authenticationService.introspect(introspectRequest);

        APIMessageDto<IntrospectDto> response = APIMessageDto.<IntrospectDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Login successfully")
                .data(introspectResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
