package com.olvera.warehouse.service;

import com.olvera.warehouse.dto.AuthResponse;
import com.olvera.warehouse.dto.LoginRequest;
import com.olvera.warehouse.dto.RegisterRequest;
import com.olvera.warehouse.exception.ResourceAlreadyExist;
import com.olvera.warehouse.jwt.JwtService;
import com.olvera.warehouse.model.*;
import com.olvera.warehouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {

        userRepository.findByUsername(request.getUsername()).ifPresent(user -> {
            throw new ResourceAlreadyExist("User", "username", request.getUsername());
        });

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExist("User", "email", request.getEmail());
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .country(request.getCountry())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

}
