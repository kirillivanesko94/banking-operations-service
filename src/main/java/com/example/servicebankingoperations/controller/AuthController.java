package com.example.servicebankingoperations.controller;

import com.example.servicebankingoperations.model.dto.JwtRequestDto;
import com.example.servicebankingoperations.model.dto.JwtResponseDto;
import com.example.servicebankingoperations.security.JwtTokenUtils;
import com.example.servicebankingoperations.service.ClientAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final JwtTokenUtils jwtTokenUtils;
    private final ClientAuthenticationService service;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtTokenUtils jwtTokenUtils, ClientAuthenticationService service, AuthenticationManager authenticationManager) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.service = service;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody JwtRequestDto authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = service.loadUserByUsername(authRequest.getUserName());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDto(token));
    }
}
