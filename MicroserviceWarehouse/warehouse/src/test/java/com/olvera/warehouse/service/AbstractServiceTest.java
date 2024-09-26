package com.olvera.warehouse.service;

import com.olvera.warehouse.dto.CartItemResponseClient;
import com.olvera.warehouse.dto.UserResponse;
import com.olvera.warehouse.dto.UsersProductResponse;
import com.olvera.warehouse.model.AppRole;
import com.olvera.warehouse.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class AbstractServiceTest {

    protected User user;

    protected Integer userId;

    protected Integer pageNo;

    protected Integer pageSize;

    protected String productId;

    protected UserResponse userResponse;

    protected UsersProductResponse usersProductResponse;

    protected CartItemResponseClient cartItemResponseClient;

    protected UsersProductResponse.ProductClientResponse productClientResponse;

    protected UsersProductResponse.ProductClientResponse productClientResponse2;

    protected List<UsersProductResponse.ProductClientResponse> products;

    protected Map<String, Object> updates = new HashMap<>();

    protected User anotherUser = new User();

    @BeforeEach
    public void prepare() {

        userId = 3459;

        anotherUser.setUserId(999);

        productId = "JDSI289";

        pageNo = 1;

        pageSize = 10;

        updates.put("username", "VictorOlvera");
        updates.put("email", "victor.olvera@gmail.com");

        userResponse = UserResponse.builder()
                .userId(userId)
                .lastname("Fernandez Sanchez")
                .firstname("Victor")
                .username("Victor23")
                .email("victor2@gmail.com")
                .mobileNumber("5599330022")
                .country("Mexico")
                .role(AppRole.USER)
                .build();

        user = User.builder()
                .userId(userId)
                .lastname("Fernandez Sanchez")
                .firstname("Victor")
                .username("Victor23")
                .email("victor2@gmail.com")
                .mobileNumber("5599330022")
                .country("Mexico")
                .role(AppRole.USER)
                .build();

        products = new ArrayList<>();

        productClientResponse = UsersProductResponse.ProductClientResponse.builder()
                .productId(productId)
                .productName("Pizza")
                .price(24.32)
                .discountPercentage(0.0)
                .image("Pizza")
                .userId(userId)
                .build();

        productClientResponse2 = UsersProductResponse.ProductClientResponse.builder()
                .productId("JDSI259")
                .productName("Pasta")
                .price(10.00)
                .discountPercentage(0.0)
                .image("Pasta")
                .userId(userId)
                .build();

        products.add(productClientResponse);
        products.add(productClientResponse2);

        usersProductResponse = UsersProductResponse.builder()
                .userId(userId)
                .lastname("Fernandez Sanchez")
                .firstname("Victor")
                .username("Victor23")
                .email("victor2@gmail.com")
                .mobileNumber("5599330022")
                .country("Mexico")
                .role(AppRole.USER)
                .products(products)
                .build();

        cartItemResponseClient =  CartItemResponseClient.builder()
                .productId("JDSI259")
                .productName("Pasta")
                .total(10.00)
                .quantity(2)
                .image("Pasta")
                .userId(userId)
                .build();

    }

}
