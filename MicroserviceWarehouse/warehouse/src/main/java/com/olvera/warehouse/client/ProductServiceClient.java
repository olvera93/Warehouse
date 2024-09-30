package com.olvera.warehouse.client;

import com.olvera.warehouse.dto.PageResponse;
import com.olvera.warehouse.dto.UsersProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static com.olvera.warehouse.util.AppConstants.*;

@FeignClient(name = "product-service", url = "${PRODUCT_SERVICE_URL}")
public interface ProductServiceClient {

    @PostMapping(value = "/product/save")
    public ResponseEntity<UsersProductResponse.ProductClientResponse> saveProduct(@RequestBody UsersProductResponse.ProductClientResponse request);

    @GetMapping("/product/usersProducts")
    public ResponseEntity<PageResponse> getUsersProducts(
            @RequestParam(value = "userId", defaultValue = "1", required = true)Long userId,
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize
    );

}
