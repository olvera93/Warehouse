package com.olvera.warehouse.product.controller;


import com.olvera.warehouse.product.model.Product;
import com.olvera.warehouse.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
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
            @ApiResponse(responseCode = "404", description = "Not found user"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping(value = "save")
    public ResponseEntity<Product> login(@Valid @RequestBody Product request) {

        Product result = productService.saveProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


}
