package com.olvera.warehouse.repository;

import com.olvera.warehouse.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
