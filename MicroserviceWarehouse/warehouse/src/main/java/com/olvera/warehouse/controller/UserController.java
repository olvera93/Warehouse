package com.olvera.warehouse.controller;

import com.olvera.warehouse.dto.ErrorResponse;
import com.olvera.warehouse.dto.PageResponse;
import com.olvera.warehouse.dto.UserResponse;
import com.olvera.warehouse.dto.UsersProductResponse;
import com.olvera.warehouse.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.olvera.warehouse.util.AppConstants.DEFAULT_PAGE_NUMBER;
import static com.olvera.warehouse.util.AppConstants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping(path = "/api/v1/auth", produces = (MediaType.APPLICATION_JSON_VALUE))
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "User", description = "Users API")
@Validated
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Save product",
            description = "The user can save your products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found user", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/saveProducts/{userId}")
    public ResponseEntity<UsersProductResponse> saveProduct(
            @PathVariable(name = "userId", required = true) Long userId,
            @RequestBody UsersProductResponse.ProductClientResponse productClientResponse) {
        UsersProductResponse result = userService.saveProducts(userId, productClientResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(
            summary = "Update User",
            description = "The user can update your information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found user", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping(value = "/users/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable(name = "userId")
            Long userId,
            @Parameter(name = "updates", description = "Values to be updated", required = true)
            @Valid @RequestBody Map<String, Object> update
    ) {
        return ResponseEntity.ok(userService.updateUser(userId, update));

    }

    @Operation(
            summary = "Get a user by id",
            description = "You can get a user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found product", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable(name = "userId")
            Long userId
    ) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }


    @Operation(
            summary = "Get all products of the user",
            description = "You can get all products of the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found product", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/usersProducts")
    public ResponseEntity<PageResponse> getUsersProduct(
            @RequestParam(value = "userId", defaultValue = "1", required = true)Integer userId,
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        PageResponse result = userService.getUsersProducts(userId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
