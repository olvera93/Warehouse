package com.olvera.warehouse.dto;


import com.olvera.warehouse.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    Integer userId;

    String username;

    String lastname;

    String firstname;

    String email;

    String mobileNumber;

    String country;

    Role role;

}
