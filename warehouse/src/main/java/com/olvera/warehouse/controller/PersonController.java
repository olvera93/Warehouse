package com.olvera.warehouse.controller;

import com.olvera.warehouse.dto.PersonDto;
import com.olvera.warehouse.entity.Person;
import com.olvera.warehouse.service.IPersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/v1", produces = (MediaType.APPLICATION_JSON_VALUE))
@Validated
public class PersonController {

    @Autowired
    private IPersonService personService;


    @PostMapping("/signUp")
    public ResponseEntity<PersonDto> createPerson(@Valid @RequestBody PersonDto personDto) {

        try {
            PersonDto response = personService.createUser(personDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e) {
            throw new RuntimeException(e);

        }

    }
}
