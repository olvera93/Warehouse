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

    protected Long userId;

    protected int pageNo;
    protected int pageSize;


    @BeforeEach
    public void prepare() {

        productId = "JDSI289";

        userId = 2L;

        pageNo = 5;

        pageSize = 10;

        productResponse = ProductResponse.builder()
                .productId(productId)
                .productName("Pizza")
                .price(24.32)
                .discountPercentage(0.0)
                .image("Pizza")
                .userId(2L)
                .build();

        product = Product.builder()
                .productId(productId)
                .productName("Pizza")
                .price(24.32)
                .discountPercentage(0.0)
                .image("Pizza")
                .userId(2L)
                .build();

    }

}
