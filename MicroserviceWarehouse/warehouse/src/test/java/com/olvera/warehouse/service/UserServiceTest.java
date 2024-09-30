package com.olvera.warehouse.service;

import com.olvera.warehouse.client.ProductServiceConsumer;
import com.olvera.warehouse.dto.CartItemResponseClient;
import com.olvera.warehouse.dto.PageResponse;
import com.olvera.warehouse.dto.UserResponse;
import com.olvera.warehouse.dto.UsersProductResponse;
import com.olvera.warehouse.exception.ResourceAlreadyExist;
import com.olvera.warehouse.exception.ResourceNotFoundException;
import com.olvera.warehouse.model.User;
import com.olvera.warehouse.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
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

        productClientResponse.setUserId(9999L);

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
    public void testGetUsersProducts_UserHasNoProducts() {
        List<CartItemResponseClient> emptyProductList = Arrays.asList();
        PageResponse emptyPageResponse = new PageResponse(emptyProductList, pageNo, pageSize, 0L, 0, true);

        when(productServiceConsumer.getUsersProducts(userId, pageNo, pageSize)).thenReturn(emptyPageResponse);
        PageResponse result = userService.getUsersProducts(userId, pageNo, pageSize);

        assertEquals(0, result.getContent().size());
        assertEquals(0, result.getTotalElements());
        assertEquals(0, result.getTotalPages());
        assertTrue(result.isLast());

        verify(productServiceConsumer).getUsersProducts(userId, pageNo, pageSize);

    }

    @Test
    public void testGetUsersProducts_MultiplePages() {

        List<CartItemResponseClient> cartItemResponseList = Arrays.asList(cartItemResponseClient);

        PageResponse pageResponseMock = new PageResponse(cartItemResponseList, pageNo, pageSize, 2L, 2, false);

        when(productServiceConsumer.getUsersProducts(userId, pageNo, pageSize)).thenReturn(pageResponseMock);

        PageResponse result = userService.getUsersProducts(userId, pageNo, pageSize);

        assertEquals(1, result.getContent().size());
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getTotalPages());
        assertFalse(result.isLast());
    }

    @Test
    public void testGetUsersProducts() {
        List<CartItemResponseClient> cartItemResponseList = Arrays.asList(cartItemResponseClient);

        PageResponse pageResponseMock = new PageResponse(cartItemResponseList, pageNo, pageSize, cartItemResponseList.size(), 2, false);

        when(productServiceConsumer.getUsersProducts(userId, pageNo, pageSize)).thenReturn(pageResponseMock);

        PageResponse result = productServiceConsumer.getUsersProducts(userId, pageNo, pageSize);

        assertEquals("Pasta", result.getContent().get(0).getProductName());
        assertEquals(3459, result.getContent().get(0).getUserId());
        assertEquals(1, result.getContent().size());
        assertEquals(1, result.getPageNo());
        assertEquals(10, result.getPageSize());
        assertEquals(1, result.getTotalElements());
        assertEquals(2, result.getTotalPages());
        assertFalse(result.isLast());
    }

    @Test
    void findByUserId() {

        // Fail case
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThatThrownBy(() -> {
            userService.getUserById(3459L);
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

    }

}
