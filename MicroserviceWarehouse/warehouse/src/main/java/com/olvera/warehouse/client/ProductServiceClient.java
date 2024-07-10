package com.olvera.warehouse.client;

import com.olvera.warehouse.dto.UsersProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service", url = "${PRODUCT_SERVICE_URL}")
public interface ProductServiceClient {

    @PostMapping(value = "/save")
    public ResponseEntity<UsersProductResponse.ProductClientResponse> saveProduct(@RequestBody UsersProductResponse.ProductClientResponse request);

}
