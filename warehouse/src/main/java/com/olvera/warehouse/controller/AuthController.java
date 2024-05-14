package com.olvera.warehouse.controller;

import com.olvera.warehouse.dto.AuthResponse;
import com.olvera.warehouse.dto.LoginRequest;
import com.olvera.warehouse.dto.RegisterRequest;
import com.olvera.warehouse.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth", produces = (MediaType.APPLICATION_JSON_VALUE))
@RequiredArgsConstructor
@Validated
@CrossOrigin("*")
@Tag(name = "Auth", description = "Authentication API")
public class AuthController {

    private final AuthService authService;


    @Operation(
            summary = "SignIn a user",
            description = "You can log in")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found user"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Operation(
            summary = "Register a user",
            description = "You can create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
