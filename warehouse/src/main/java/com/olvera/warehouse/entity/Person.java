package com.olvera.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
@Builder
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    private String name;

    private String lastName;

    private String email;

    private String mobileNumber;

    private String password;

    private String matchPassword;

    private LocalDate birthDate;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;


}
