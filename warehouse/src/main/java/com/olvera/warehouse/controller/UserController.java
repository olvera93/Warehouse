package com.olvera.warehouse.controller;

import com.olvera.warehouse.dto.UserResponse;
import com.olvera.warehouse.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1", produces = (MediaType.APPLICATION_JSON_VALUE))
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "User", description = "Users API")
@Validated
public class UserController {

    private final UserService userService;

    @PatchMapping(value = "/users/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable(name = "userId")
            Integer userId,
            @Parameter(name = "updates", description = "Values to be updated", required = true)
            @Valid @RequestBody Map<String, Object> update
    ) {
        return ResponseEntity.ok(userService.updateUser(userId, update));

    }

    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable(name = "userId")
            Integer userId
    ) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
