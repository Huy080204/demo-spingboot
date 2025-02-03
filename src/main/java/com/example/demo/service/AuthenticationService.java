package com.example.demo.service;

import com.example.demo.dto.request.authentication.AuthenticationRequest;
import com.example.demo.dto.request.authentication.IntrospectRequest;
import com.example.demo.dto.response.authentication.AuthenticationResponse;
import com.example.demo.dto.response.authentication.IntrospectResponse;

import java.text.ParseException;
import com.nimbusds.jose.JOSEException;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);

    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;

}
