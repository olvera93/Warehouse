package com.olvera.warehouse.service;

import com.olvera.warehouse.client.ProductServiceConsumer;
import com.olvera.warehouse.dto.UserResponse;
import com.olvera.warehouse.dto.UsersProductResponse;
import com.olvera.warehouse.exception.ResourceAlreadyExist;
import com.olvera.warehouse.exception.ResourceNotFoundException;
import com.olvera.warehouse.model.User;
import com.olvera.warehouse.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ProductServiceConsumer productServiceConsumer;

    public UsersProductResponse saveProducts(Integer userId, UsersProductResponse.ProductClientResponse productClientResponse) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "UserId", userId.toString()));

        if (!user.getUserId().equals(productClientResponse.getUserId())) {
            throw new IllegalArgumentException("The userId doesn't match");
        }

        List<UsersProductResponse.ProductClientResponse> productClientResponseList = new ArrayList<>();

        UsersProductResponse.ProductClientResponse saveProductClientResponse = UsersProductResponse.ProductClientResponse.builder()
                .productId(productClientResponse.getProductId())
                .productName(productClientResponse.getProductName())
                .price(productClientResponse.getPrice())
                .discountPercentage(productClientResponse.getDiscountPercentage())
                .image(productClientResponse.getImage())
                .userId(productClientResponse.getUserId())
                .build();

        productClientResponseList.add(saveProductClientResponse);

        productServiceConsumer.saveProduct(saveProductClientResponse);

        return UsersProductResponse.builder()
                .userId(userId)
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .country(user.getCountry())
                .products(productClientResponseList)
                .build();

    }

    public UserResponse getUserById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "UserId", userId.toString()));

        return UserResponse.builder()
                .userId(userId)
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .country(user.getCountry())
                .build();
    }

    @Transactional
    public UserResponse updateUser(Integer userId, Map<String, Object> updates) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "UserId", userId.toString()));

        List<String> allowedProperties = Arrays.asList("username", "password", "firstname", "lastname", "email", "mobileNumber", "country");

        updates.keySet().forEach(key -> {
            if (!allowedProperties.contains(key)) {
                throw new IllegalArgumentException("Property: " + key + " is not a valid property to update");
            }
        });

        String propertyToUpdate = "username";
        if (updates.containsKey("username") && updates.get("username") != null) {
            String newUsername = updates.get(propertyToUpdate).toString();
            userRepository.findByUsername(newUsername).ifPresent(existingUser -> {
                if (!existingUser.getUserId().equals(userId)) {
                    throw new ResourceAlreadyExist("User", "username", newUsername);
                }
            });
            nullOrEmptyValidation(newUsername, "Username cannot be null or empty ");
            user.setUsername(newUsername);
        }

        propertyToUpdate = "firstname";
        if (updates.containsKey("firstname") && updates.get("firstname") != null) {
            String newFirstname = updates.get(propertyToUpdate).toString();
            nullOrEmptyValidation(newFirstname, "Firstname cannot be null or empty ");
            user.setFirstname(newFirstname);
        }

        propertyToUpdate = "lastname";
        if (updates.containsKey("lastname") && updates.get("lastname") != null) {
            String newLastname = updates.get(propertyToUpdate).toString();
            userRepository.findByLastname(newLastname).ifPresent(existingUser -> {
                if (!existingUser.getUserId().equals(userId)) {
                    throw new ResourceAlreadyExist("User", "lastname", newLastname);
                }
            });
            nullOrEmptyValidation(newLastname, "Lastname cannot be null or empty ");
            user.setLastname(newLastname);
        }

        propertyToUpdate = "email";
        if (updates.containsKey(propertyToUpdate) && updates.get(propertyToUpdate) != null) {
            String newEmail = updates.get(propertyToUpdate).toString();
            if (userRepository.existsByEmail(newEmail)) {
                throw new ResourceAlreadyExist("User", "email", newEmail);
            }
            nullOrEmptyValidation(newEmail, "Email cannot be null or empty");
            user.setEmail(newEmail);
        }

        propertyToUpdate = "mobileNumber";
        if (updates.containsKey(propertyToUpdate) && updates.get(propertyToUpdate) != null) {
            String newNumber = updates.get(propertyToUpdate).toString();
            if (userRepository.existsByMobileNumber(newNumber)) {
                throw new ResourceAlreadyExist("User", "mobileNumber", newNumber);
            }
            nullOrEmptyValidation(newNumber, "Mobile number cannot be null or empty");
            user.setMobileNumber(newNumber);
        }

        propertyToUpdate = "country";
        if (updates.containsKey(propertyToUpdate) && updates.get(propertyToUpdate) != null) {
            String newCountry = updates.get(propertyToUpdate).toString();
            nullOrEmptyValidation(newCountry, "Country cannot be null or empty");
            user.setCountry(newCountry);
        }

        User userReturned = userRepository.save(user);

        return UserResponse.builder()
                .userId(userId)
                .username(userReturned.getUsername())
                .firstname(userReturned.getFirstname())
                .lastname(userReturned.getLastname())
                .email(userReturned.getEmail())
                .mobileNumber(userReturned.getMobileNumber())
                .country(userReturned.getCountry())
                .role(userReturned.getRole())
                .build();

    }

    private void nullOrEmptyValidation(String field, String message) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

}
