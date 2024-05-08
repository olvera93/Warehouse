package com.olvera.warehouse.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    String username;

    String password;

    String firstname;

    String lastname;

    String country;

}
