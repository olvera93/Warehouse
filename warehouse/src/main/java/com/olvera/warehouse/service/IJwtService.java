package com.olvera.warehouse.service;

import com.olvera.warehouse.entity.Person;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public interface IJwtService {

    String generateToken(Person person);

    <T> T extractClaim(String token, Function<Claims, T> resolver);

    String extractUsername(String token);

    boolean isValid(String token, UserDetails person);
}
