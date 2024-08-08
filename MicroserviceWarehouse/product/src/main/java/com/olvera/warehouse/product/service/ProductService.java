package com.olvera.warehouse.product.service;

import com.olvera.warehouse.product.dto.ProductResponse;
import com.olvera.warehouse.product.exception.ResourceNotFoundException;
import com.olvera.warehouse.product.model.Product;
import com.olvera.warehouse.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse saveProduct(ProductResponse product) {

        Product newProduct = Product.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .discountPercentage(product.getDiscountPercentage())
                .image(product.getImage())
                .userId(product.getUserId())
                .build();

        productRepository.save(newProduct);

        return ProductResponse.builder()
                .productId(newProduct.getProductId())
                .productName(newProduct.getProductName())
                .price(newProduct.getPrice())
                .discountPercentage(newProduct.getDiscountPercentage())
                .image(newProduct.getImage())
                .userId(newProduct.getUserId())
                .build();
    }

    public ProductResponse getProductById(String productId) {

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", productId)
        );

        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .discountPercentage(product.getDiscountPercentage())
                .image(product.getImage())
                .userId(product.getUserId())
                .build();

    }

    public void deleteProductById(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", productId)
        );
        productRepository.delete(product);
    }

}
