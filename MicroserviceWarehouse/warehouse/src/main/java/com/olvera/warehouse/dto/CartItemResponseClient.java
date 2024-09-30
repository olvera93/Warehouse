package com.olvera.warehouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponseClient implements Serializable {

    private String productId;

    @Schema(name = "productName", description = "The product's name", example = "Pizza")
    private String productName;

    @Schema(name = "price", description = "Price of the product", example = "15.20")
    private double total;

    @Schema(name = "quantity", description = "Quantity of the product", example = "3")
    private  int quantity;

    @Schema(name = "image", description = "Image of the product", example = "Pizza")
    private String image;

    @Schema(name = "userId", description = "To which product(s) the user belongs ", example = "2")
    Long userId;

}
