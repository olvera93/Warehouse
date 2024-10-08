package com.olvera.warehouse.product.controller;


import com.olvera.warehouse.product.dto.ErrorResponse;
import com.olvera.warehouse.product.dto.PageResponse;
import com.olvera.warehouse.product.dto.ProductResponse;
import com.olvera.warehouse.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
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

import static com.olvera.warehouse.product.util.AppConstants.DEFAULT_PAGE_NUMBER;
import static com.olvera.warehouse.product.util.AppConstants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping(path = "/product", produces = (MediaType.APPLICATION_JSON_VALUE))
@RequiredArgsConstructor
@Validated
@CrossOrigin("*")
@Tag(name = "Products", description = "WarehouseProducts API")
public class ProductController {

    private final ProductService productService;


    @Operation(
            summary = "Save a product",
            description = "You can save product of a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found product", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error with the server", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))

    })
    @PostMapping(value = "/save")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductResponse request) {

        ProductResponse result = productService.saveProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(
            summary = "Get a product by id",
            description = "You can get a product by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found product", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error with the server", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/products/{productId}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable(name = "productId", required = true) String productId
    ) {
        ProductResponse result = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @Operation(
            summary = "Get user's products",
            description = "You can get all products for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found product", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error with the server", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/usersProducts")
    public ResponseEntity<PageResponse> getUsersProducts(
            @RequestParam(value = "userId", defaultValue = "1", required = true)Integer userId,
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        PageResponse result = productService.getUsersProducts(userId, pageNo, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @Operation(summary = "Delete product", description = "Deletes product if exists")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error while deleting product", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    @DeleteMapping(path = "/products/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable(name = "productId", required = true) String productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
