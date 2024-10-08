package com.olvera.warehouse.product.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@Document(collection = "Product")
public class Product implements Serializable {

    @Id
    private String productId;

    private String productName;

    private double price;

    private double discountPercentage;

    private String image;

    private Integer userId;

}
