package com.olvera.warehouse.dto;

import com.olvera.warehouse.model.AppRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersProductResponse implements Serializable {

    Long userId;

    @NotEmpty(message = "Username can not be a null or empty")
    @Schema(name = "username", description = "The user's nickname", example = "Paco234")
    String username;

    @NotEmpty(message = "LastName can not be a null or empty")
    @Size(min = 3, max = 30, message = "The length of the lastname should be between 5 and 30")
    @Schema(name = "lastname", description = "The user's lastname", example = "Sanchez Perez")
    String lastname;

    @NotEmpty(message = "Firstname can not be a null or empty")
    @Size(min = 3, max = 30, message = "The length of the firstname should be between 2 and 30")
    @Schema(name = "firstname", description = "The user's firstname", example = "Paco")
    String firstname;

    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    @Schema(name = "email", description = "The user's email", example = "paco2@gmail.com")
    String email;

    @Pattern(regexp = "($|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(name = "mobileNumber", description = "The user's mobileNumber", example = "5599443399")
    String mobileNumber;

    @NotEmpty(message = "Country can not be a null or empty")
    @Schema(name = "country", description = "The user's country", example = "Mexico")
    String country;

    AppRole role;

    List<ProductClientResponse> products;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductClientResponse {

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

}
