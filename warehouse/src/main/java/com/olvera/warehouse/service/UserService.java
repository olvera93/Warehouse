package com.olvera.warehouse.service;

import com.olvera.warehouse.dto.UserResponse;
import com.olvera.warehouse.exception.ResourceNotFoundException;
import com.olvera.warehouse.model.User;
import com.olvera.warehouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUserById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "UserId", userId.toString()));

        return UserResponse.builder()
                .userId(userId)
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .country(user.getCountry())
                .build();
    }


}
