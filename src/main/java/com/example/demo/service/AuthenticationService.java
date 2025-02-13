package com.example.demo.service;

import com.example.demo.form.authentication.AuthenticationForm;
import com.example.demo.form.authentication.IntrospectForm;
import com.example.demo.dto.authentication.AuthenticationDto;
import com.example.demo.dto.authentication.IntrospectDto;

import java.text.ParseException;
import com.nimbusds.jose.JOSEException;

public interface AuthenticationService {

    AuthenticationDto authenticate(AuthenticationForm request);

}
