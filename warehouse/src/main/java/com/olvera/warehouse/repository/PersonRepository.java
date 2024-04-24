package com.olvera.warehouse.repository;

import com.olvera.warehouse.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByMobileNumber(String mobileMobile);

    Person findByEmail(String email);

    Optional<Person> findByName(String name);
}
