package com.olvera.warehouse.client;

import com.olvera.warehouse.dto.PageResponse;
import com.olvera.warehouse.dto.UsersProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductServiceConsumer {

    private final ProductServiceClient productServiceClient;

    public UsersProductResponse.ProductClientResponse saveProduct(UsersProductResponse.ProductClientResponse productClientResponse) {
        return productServiceClient.saveProduct(productClientResponse).getBody();
    }

    public PageResponse getUsersProducts(Integer userId, int pageNo, int pageSize ) {
        return productServiceClient.getUsersProducts(userId, pageNo, pageSize).getBody();
    }

}
