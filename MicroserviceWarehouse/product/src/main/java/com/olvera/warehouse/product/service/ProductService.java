package com.olvera.warehouse.product.service;

import com.olvera.warehouse.product.model.Product;
import com.olvera.warehouse.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product saveProduct(Product product) {

        Product newProduct = Product.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .discountPercentage(product.getDiscountPercentage())
                .image(product.getImage())
                .build();

        return productRepository.save(newProduct);
    }

}
