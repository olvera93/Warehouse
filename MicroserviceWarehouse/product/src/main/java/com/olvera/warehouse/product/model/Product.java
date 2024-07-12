package com.olvera.warehouse.product.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "Product")
public class Product {

    @Id
    private String productId;

    private String productName;

    private double price;

    private double discountPercentage;

    private String image;

}
