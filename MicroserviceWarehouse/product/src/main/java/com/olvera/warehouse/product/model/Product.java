package com.olvera.warehouse.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Product")
public class Product {

    @Id
    private String productId;

    private String productName;

}
