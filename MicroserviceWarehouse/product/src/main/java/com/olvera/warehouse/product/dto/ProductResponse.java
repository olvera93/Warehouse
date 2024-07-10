package com.olvera.warehouse.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String productId;

    @Schema(name = "productName", description = "The product's name", example = "Pizza")
    private String productName;

    @Schema(name = "price", description = "Price of the product", example = "15.20")
    private double price;

    @Schema(name = "discountPercentage", description = "Discount if the product has", example = "15")
    private double discountPercentage;

    @Schema(name = "image", description = "Image of the product", example = "Pizza")
    private String image;

    @Schema(name = "userId", description = "To which product(s) the user belongs ", example = "2")
    Integer userId;
}
