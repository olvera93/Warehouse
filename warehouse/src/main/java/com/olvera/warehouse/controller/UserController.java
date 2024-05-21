package com.olvera.warehouse.controller;

import com.olvera.warehouse.dto.UserResponse;
import com.olvera.warehouse.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1", produces = (MediaType.APPLICATION_JSON_VALUE))
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "User", description = "Users API")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable(name = "userId")
            Integer userId
    ) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
