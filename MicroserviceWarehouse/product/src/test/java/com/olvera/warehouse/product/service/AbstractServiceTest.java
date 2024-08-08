package com.olvera.warehouse.product.service;

import com.olvera.warehouse.product.dto.ProductResponse;
import com.olvera.warehouse.product.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AbstractServiceTest {

    protected Product product;

    protected String productId;

    protected ProductResponse productResponse;


    @BeforeEach
    public void prepare() {
        productId = "JDSI289";

        productResponse = ProductResponse.builder()
                .productId(productId)
                .productName("Pizza")
                .price(24.32)
                .discountPercentage(0.0)
                .image("Pizza")
                .userId(2)
                .build();

        product = Product.builder()
                .productId(productId)
                .productName("Pizza")
                .price(24.32)
                .discountPercentage(0.0)
                .image("Pizza")
                .userId(2)
                .build();

    }

}
