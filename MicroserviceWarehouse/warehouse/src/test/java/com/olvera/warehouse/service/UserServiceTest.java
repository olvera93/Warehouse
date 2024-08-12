package com.olvera.warehouse.service;

import com.olvera.warehouse.client.ProductServiceConsumer;
import com.olvera.warehouse.dto.UserResponse;
import com.olvera.warehouse.dto.UsersProductResponse;
import com.olvera.warehouse.exception.ResourceAlreadyExist;
import com.olvera.warehouse.exception.ResourceNotFoundException;
import com.olvera.warehouse.model.User;
import com.olvera.warehouse.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest extends AbstractServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductServiceConsumer productServiceConsumer;


    @Test
    public void saveProducts_shouldSaveAndReturnUserProductResponse() {

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UsersProductResponse result = userService.saveProducts(userId, productClientResponse);

        assertNotNull(result);
        assertEquals(1, result.getProducts().size());
        assertEquals(userId, result.getUserId());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(productId, result.getProducts().get(0).getProductId());
        assertEquals("Pizza", result.getProducts().get(0).getProductName());

        verify(productServiceConsumer, times(1)).saveProduct(any(UsersProductResponse.ProductClientResponse.class));
    }

    @Test
    void saveProducts_shouldThrowExceptionWhenUserNotFound() {

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.saveProducts(userId, productClientResponse))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("User");

        verify(productServiceConsumer, never()).saveProduct(any(UsersProductResponse.ProductClientResponse.class));
    }

    @Test
    public void saveProducts_shouldThrowExceptionWhenUserIdDoesNotMatch() {

        productClientResponse.setUserId(9999);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        assertThatThrownBy(() -> userService.saveProducts(userId, productClientResponse))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("userId doesn't match");

        verify(productServiceConsumer, never()).saveProduct(any(UsersProductResponse.ProductClientResponse.class));
    }

    @Test
    public void updateUser_shouldUpdateUserSuccessfully() {

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        when(userRepository.findByUsername("VictorOlvera")).thenReturn(Optional.empty());
        when(userRepository.existsByEmail("victor.olvera@gmail.com")).thenReturn(false);

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserResponse result = userService.updateUser(userId, updates);

        assertEquals("VictorOlvera", result.getUsername());
        assertEquals("victor.olvera@gmail.com", result.getEmail());
        assertEquals(userId, result.getUserId());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void updateUser_shouldThrowExceptionWhenPropertyIsNotAllowed() {

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        updates.put("notAllowedProperty", "value");

        assertThatThrownBy(() -> userService.updateUser(userId, updates))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Property: notAllowedProperty is not a valid property to update");
    }

    @Test
    public void updateUser_shouldThrowExceptionWhenUsernameAlreadyExists() {

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        when(userRepository.findByUsername("Victor23")).thenReturn(Optional.of(anotherUser));

        updates.put("username", "Victor23");

        assertThatThrownBy(() -> userService.updateUser(userId, updates))
                .isInstanceOf(ResourceAlreadyExist.class)
                .hasMessageContaining("username");
    }

    @Test
    public void updateUser_shouldThrowExceptionWhenEmailAlreadyExists() {

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        when(userRepository.existsByEmail("victor.olvera@gmail.com")).thenReturn(true);

        updates.put("email", "victor.olvera@gmail.com");

        assertThatThrownBy(() -> userService.updateUser(userId, updates))
                .isInstanceOf(ResourceAlreadyExist.class)
                .hasMessageContaining("email");

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void findByUserId() {

        // Fail case
        when(userRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        assertThatThrownBy(() -> {
            userService.getUserById(3459);
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("User not found with the given input data UserId: '3459'");

        // Success case
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userResponse = userService.getUserById(userId);
        assertNotNull(userResponse);
        assertEquals(3459, userResponse.getUserId());
        assertEquals("Fernandez Sanchez", userResponse.getLastname());
        assertEquals("Victor", userResponse.getFirstname());
        assertEquals("Victor23", userResponse.getUsername());
        assertEquals("victor2@gmail.com", userResponse.getEmail());
        assertEquals("5599330022", userResponse.getMobileNumber());
        assertEquals("Mexico", userResponse.getCountry());

    }

}
