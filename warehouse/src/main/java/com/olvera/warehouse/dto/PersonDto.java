package com.olvera.warehouse.dto;

import com.olvera.warehouse.entity.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private Long personId;

    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 3, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @NotEmpty(message = "LastName can not be a null or empty")
    @Size(min = 3, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String lastName;

    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Pattern(regexp = "($|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "Password can not be a null or empty")
    @Size(min = 8, max = 16, message = "Password is 8-16 characters with no space")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$", message = "Password is 8-16 characters with at least one digit, one uppercase letter, one lowercase letter, and one special character")
    private String password;

    @NotEmpty(message = "Match Password can not be a null or empty")
    private String matchPassword;

    @Size(max = 10, message = "birthDate length must not be bigger than 10 characters")
    @NotEmpty(message = "birthDate field is required")
    private String birthDate;

    private Role role;

    private String token;

}
