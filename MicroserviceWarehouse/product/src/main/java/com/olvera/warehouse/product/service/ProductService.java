package com.olvera.warehouse.product.service;

import com.olvera.warehouse.product.dto.PageResponse;
import com.olvera.warehouse.product.dto.CartItem;
import com.olvera.warehouse.product.dto.ProductResponse;
import com.olvera.warehouse.product.exception.ResourceNotFoundException;
import com.olvera.warehouse.product.model.Product;
import com.olvera.warehouse.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.olvera.warehouse.product.util.AppConstants.PRODUCT_CACHE;
import static com.olvera.warehouse.product.util.AppConstants.PRODUCT_CACHE_LIST;

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

    @Cacheable(value = PRODUCT_CACHE)
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

    @Cacheable(value = PRODUCT_CACHE_LIST)
    public PageResponse getUsersProducts(Long userId, int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Product> products = productRepository.findByUserId(userId, pageable);

        Map<String, CartItem> groupedCartItems = new HashMap<>();

        products.getContent().forEach(product -> {
            CartItem cartItem = mapToProductResponse(product);
            String productName = cartItem.getProductName();

            groupedCartItems.compute(productName, (key, existingCartItem) -> {
                if (existingCartItem == null) {
                    return cartItem;
                } else {
                    existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
                    existingCartItem.setTotal(existingCartItem.getQuantity() * cartItem.getTotal());
                    return existingCartItem;
                }
            });
        });

        List<CartItem> content = new ArrayList<>(groupedCartItems.values());

        return new PageResponse(
                content,
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isLast()
        );
    }

    public void deleteProductById(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", productId)
        );
        productRepository.delete(product);
    }

    private CartItem mapToProductResponse(Product product) {

        double total = product.getPrice() - (product.getPrice() * product.getDiscountPercentage());

        return CartItem.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .total(total)
                .image(product.getImage())
                .quantity(1)
                .userId(product.getUserId())
                .build();
    }
}
