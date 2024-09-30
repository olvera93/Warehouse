package com.olvera.warehouse.product.service;

import com.olvera.warehouse.product.dto.PageResponse;
import com.olvera.warehouse.product.dto.ProductResponse;
import com.olvera.warehouse.product.exception.ResourceNotFoundException;
import com.olvera.warehouse.product.model.Product;
import com.olvera.warehouse.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
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
    public void testGetUsersProducts_UserHasNoProducts() {
        userId = 1L;
        pageNo = 0;
        pageSize = 10;

        List<Product> emptyProductList = Arrays.asList();
        Page<Product> emptyProductPage = new PageImpl<>(emptyProductList, PageRequest.of(pageNo, pageSize), 0);

        when(productRepository.findByUserId(eq(userId), any(Pageable.class)))
                .thenReturn(emptyProductPage);

        PageResponse pageResponse = productService.getUsersProducts(userId, pageNo, pageSize);

        assertEquals(0, pageResponse.getContent().size());
        assertEquals(0, pageResponse.getTotalElements());
        assertEquals(0, pageResponse.getTotalPages());
        assertTrue(pageResponse.isLast());
    }

    @Test
    public void testGetUsersProducts_MultiplePages() {
        pageNo = 0;
        pageSize = 1;

        List<Product> productList = Arrays.asList(product);
        Page<Product> productPage = new PageImpl<>(productList, PageRequest.of(pageNo, pageSize), 2);

        when(productRepository.findByUserId(eq(userId), any(Pageable.class)))
                .thenReturn(productPage);

        PageResponse pageResponse = productService.getUsersProducts(userId, pageNo, pageSize);

        assertEquals(1, pageResponse.getContent().size());
        assertEquals(2, pageResponse.getTotalElements());
        assertEquals(2, pageResponse.getTotalPages());
        assertFalse(pageResponse.isLast());
    }



    @Test
    public void testGetUsersProducts() {

        List<Product> productList = Arrays.asList(product, product);
        Page<Product> productPage = new PageImpl<>(productList, PageRequest.of(pageNo, pageSize), productList.size());

        when(productRepository.findByUserId(eq(userId), any(Pageable.class)))
                .thenReturn(productPage);

        PageResponse pageResponse = productService.getUsersProducts(userId, pageNo, pageSize);

        assertEquals("Pizza", pageResponse.getContent().get(0).getProductName());
        assertEquals(2, pageResponse.getContent().get(0).getUserId());
        assertEquals(1, pageResponse.getContent().size());
        assertEquals(5, pageResponse.getPageNo());
        assertEquals(10, pageResponse.getPageSize());
        assertEquals(52, pageResponse.getTotalElements());
        assertEquals(6, pageResponse.getTotalPages());
        assertTrue(pageResponse.isLast());
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
    public void testGetUsersProducts_PageOutOfRange() {
        int pageNo = 10;
        int pageSize = 10;

        List<Product> emptyProductList = Arrays.asList();
        Page<Product> emptyProductPage = new PageImpl<>(emptyProductList, PageRequest.of(pageNo, pageSize), 0);

        when(productRepository.findByUserId(eq(userId), any(Pageable.class)))
                .thenReturn(emptyProductPage);

        PageResponse pageResponse = productService.getUsersProducts(userId, pageNo, pageSize);

        assertEquals(0, pageResponse.getContent().size());
        assertEquals(0, pageResponse.getTotalElements());
        assertEquals(0, pageResponse.getTotalPages());
        assertTrue(pageResponse.isLast());
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
