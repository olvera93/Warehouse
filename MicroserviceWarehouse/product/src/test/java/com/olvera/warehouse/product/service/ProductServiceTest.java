package com.olvera.warehouse.product.service;

import com.olvera.warehouse.product.dto.ProductResponse;
import com.olvera.warehouse.product.exception.ResourceNotFoundException;
import com.olvera.warehouse.product.model.Product;
import com.olvera.warehouse.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest extends AbstractServiceTest{

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void saveProduct() {

        when(productRepository.save(any(Product.class))).thenAnswer(new Answer<Product>() {

            @Override
            public Product answer(InvocationOnMock invocationOnMock) throws Throwable {
                Product savedProduct = invocationOnMock.getArgument(0);
                assertNotNull(savedProduct);
                savedProduct.setProductId("JDSI289");
                assertEquals("JDSI289", savedProduct.getProductId());
                assertEquals("Pizza", savedProduct.getProductName());
                assertEquals(24.32, savedProduct.getPrice());
                assertEquals(0.0, savedProduct.getDiscountPercentage());
                assertEquals("Pizza", savedProduct.getImage());
                assertEquals(2, savedProduct.getUserId());
                return savedProduct;
            }
        });

        ProductResponse result = productService.saveProduct(productResponse);
        assertEquals(productId, result.getProductId());
        assertEquals("Pizza", result.getProductName());
        assertEquals(24.32, result.getPrice());
        assertEquals(0.0, result.getDiscountPercentage());
        assertEquals("Pizza", result.getImage());
        assertEquals(2, result.getUserId());

        verify(productRepository, times(1)).save(any(Product.class));

    }

    @Test
    public void findById() {

        // Fail case
        when(productRepository.findById(any(String.class))).thenReturn(Optional.empty());
        assertThatThrownBy(() -> {
            productService.getProductById("JDSI289");
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Product not found with the given input data id: 'JDSI289'");

        // Success case
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        productResponse = productService.getProductById(productId);
        assertEquals(productId, productResponse.getProductId());
        assertEquals("Pizza", productResponse.getProductName());
        assertEquals(24.32, productResponse.getPrice());
        assertEquals(0.0, productResponse.getDiscountPercentage());
        assertEquals("Pizza", productResponse.getImage());
        assertEquals(2, productResponse.getUserId());
    }

    @Test
    public void deleteProductById() {

        // Fail case
        when(productRepository.findById(any(String.class))).thenReturn(Optional.empty());
        assertThatThrownBy(() -> {
            productService.getProductById("JDSI289");
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Product not found with the given input data id: 'JDSI289'");

        // Success case
        when(productRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(product));
        productService.deleteProductById(productId);

        verify(productRepository).delete(product);

    }

}
